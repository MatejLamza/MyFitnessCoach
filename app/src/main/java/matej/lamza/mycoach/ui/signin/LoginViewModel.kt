package matej.lamza.mycoach.ui.signin

import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.AdditionalUserInfo
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import matej.lamza.mycoach.BuildConfig
import matej.lamza.mycoach.common.ErrorMessage
import matej.lamza.mycoach.common.exception.UnknownErrorException
import matej.lamza.mycoach.common.state.State
import matej.lamza.mycoach.common.state.UiState
import matej.lamza.mycoach.data.local.session.SessionProvider
import matej.lamza.mycoach.data.repo.auth.AuthRepo
import matej.lamza.mycoach.utils.ErrorMapper
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

sealed interface LoginUIState : UiState {
    override val isLoading: Boolean
    override var errorMessages: List<ErrorMessage>

    data class LoginSuccess(
        override val isLoading: Boolean,
        val tokenID: String,
        override var errorMessages: List<ErrorMessage>
    ) : LoginUIState

    data class LoginFailure(override val isLoading: Boolean, override var errorMessages: List<ErrorMessage>) :
        LoginUIState
}

private data class ViewModelState(
    val isLoading: Boolean = false,
    val tokenID: String? = null,
    val errorMessages: List<ErrorMessage> = emptyList()
) {
    fun mapToUIState(): LoginUIState =
        if (tokenID == null) LoginUIState.LoginFailure(isLoading, errorMessages)
        else LoginUIState.LoginSuccess(isLoading, tokenID, errorMessages)
}

class LoginViewModel(private val sessionProvider: SessionProvider, private val authRepo: AuthRepo) : ViewModel() {

    private val viewModelState = MutableStateFlow(ViewModelState(isLoading = true))
    val uiState = viewModelState
        .map(ViewModelState::mapToUIState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.mapToUIState())

    fun signIn(
        oneTapClient: SignInClient,
        signInLauncher: ActivityResultLauncher<IntentSenderRequest>
    ) {
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(BuildConfig.WEB_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()


        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener { result ->
                runCatching { signInLauncher.launch(IntentSenderRequest.Builder(result.pendingIntent).build()) }
                    .onFailure {
                        viewModelState.update {
                            it.copy(
                                isLoading = false,
                                errorMessages = it.errorMessages + UnknownErrorException().toErrorMessage()
                            )
                        }
                    }
            }
            .addOnFailureListener { exception ->
                viewModelState.update {
                    it.copy(isLoading = false, errorMessages = it.errorMessages + ErrorMapper.mapException(exception))
                }
            }
    }

    fun authenticateWithFirebase(signInCredential: SignInCredential) {
        launchWithState(viewModelState) {
            return@launchWithState kotlin.runCatching {
                val temp = authRepo.signIn(signInCredential)
                Log.d(
                    "AuthResult",
                    "Auth result : \n User: ${userString(temp.user)} \n Credentials: ${temp.credential.toString()} \n Info: ${
                        addintionalInfo(
                            temp.additionalUserInfo
                        )
                    }"
                )
                State.Done(data = "true")
            }
                .onFailure { State.Error(it) }
                .getOrDefault(State.Error(UnknownErrorException()))
        }
    }

    private fun userString(user: FirebaseUser?) =
        """
            tennant: ${user?.tenantId}
            display name: ${user?.displayName}
            Email : ${user?.email}
            UID : ${user?.uid}
            Provider id : ${user?.providerId}
            Phone number : ${user?.phoneNumber}
        """.trimIndent()

    private fun addintionalInfo(additionalUserInfo: AdditionalUserInfo?) =
        """
          username : ${additionalUserInfo?.username}
          isNewUser : ${additionalUserInfo?.isNewUser}
          profile: ${additionalUserInfo?.profile}
          provider : ${additionalUserInfo?.providerId}
      """.trimIndent()


    fun errorShown(errorId: Long) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }

    private fun ViewModel.launchWithState(
        state: MutableStateFlow<ViewModelState>,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope .() -> State<String>
    ) {
        viewModelScope.launch(context, start) {
            val result = block(this)
            state.update {
                when (result) {
                    is State.Done -> it.copy(isLoading = false, tokenID = result.data)
                    is State.Error -> {
                        val error = ErrorMapper.mapError(result.error)
                        val errorMessages = it.errorMessages + error
                        it.copy(isLoading = false, errorMessages = errorMessages)
                    }
                    else -> throw UnknownErrorException()
                }
            }
        }
    }
}
