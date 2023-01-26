package matej.lamza.mycoach.ui.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import matej.lamza.mycoach.common.ErrorMessage
import matej.lamza.mycoach.common.state.State
import matej.lamza.mycoach.data.User
import matej.lamza.mycoach.data.local.session.SessionProvider
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

sealed interface SignInUIState {
    val isLoading: Boolean

    data class Success(override val isLoading: Boolean) : SignInUIState
    data class Failure(override val isLoading: Boolean, val errorMessages: List<ErrorMessage>) : SignInUIState
}

private data class SignInViewModelState(
    val isLoading: Boolean = false,
    val isLoggedInSuccessfully: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList()
) {
    fun toUIState(): SignInUIState =
        if (isLoggedInSuccessfully) SignInUIState.Success(isLoading)
        else SignInUIState.Failure(isLoading, errorMessages)
}

class SignInViewModel(private val sessionProvider: SessionProvider) : ViewModel() {

    private val viewModelState = MutableStateFlow(SignInViewModelState(isLoading = true))
    val uiState = viewModelState
        .map { it.toUIState() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUIState())


    fun googleSignIn(authCredential: SignInCredential) {
        launchWithState(viewModelState) {
            kotlin.runCatching {
                Log.d("bbb", "googleSignIn: ")
//                sessionProvider.login(authCredential)
                Log.d("bbb", "googleSignIn: gotovo")
                State.Done(true)
            }
                .onFailure { State.Error(it) }
                .getOrDefault(State.Error(IllegalStateException("Default ")))
        }
    }
}

private fun ViewModel.launchWithState(
    state: MutableStateFlow<SignInViewModelState>,
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> State<Boolean>
) {
    viewModelScope.launch(context, start) {
        val result = block(this)
        state.update {
            when (result) {
                is State.Done -> it.copy(isLoading = false, isLoggedInSuccessfully = true)
                is State.Error -> it.copy(
                    isLoading = false,
                    errorMessages = it.errorMessages,
                    isLoggedInSuccessfully = false
                )
                else -> throw IllegalStateException("State is neither Done nor Error")
            }
        }
    }
}
