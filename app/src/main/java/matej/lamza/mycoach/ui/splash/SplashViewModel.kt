package matej.lamza.mycoach.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import matej.lamza.mycoach.R
import matej.lamza.mycoach.common.ErrorMessage
import matej.lamza.mycoach.common.exception.SessionNotFound
import matej.lamza.mycoach.common.state.State
import matej.lamza.mycoach.data.local.session.SessionPrefs
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

sealed interface SplashUIState {
    val isLoading: Boolean

    data class UserFound(override val isLoading: Boolean, val isUserFound: Boolean) : SplashUIState
    data class UserNotFound(override val isLoading: Boolean, val errorMessages: List<ErrorMessage>) : SplashUIState
}

private data class SplashViewModelState(
    val isUserFound: Boolean? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList()
) {
    fun toUIState(): SplashUIState =
        if (isUserFound == null || isUserFound == false)
            SplashUIState.UserNotFound(isLoading = isLoading, errorMessages = errorMessages)
        else
            SplashUIState.UserFound(isLoading = isLoading, isUserFound = isUserFound)
}

class SplashViewModel(private val sessionPrefs: SessionPrefs, private val firebaseAuth: FirebaseAuth) : ViewModel() {

    private val viewModelState = MutableStateFlow(SplashViewModelState(isLoading = true))
    val uiState = viewModelState
        .map(SplashViewModelState::toUIState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUIState())

    init {
        viewModelScope.launch {
            processSession()
        }
    }

    private fun processSession() =
        launchWithState(viewModelState) {
            val doesSessionExist = sessionPrefs.getUser() != null && firebaseAuth.currentUser != null
            if (!doesSessionExist) State.Error(SessionNotFound())
            else State.Done(data = true)
        }

    private fun ViewModel.launchWithState(
        state: MutableStateFlow<SplashViewModelState>,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> State<Boolean>
    ) {
        viewModelScope.launch(context, start) {
            val result = block(this)
            state.update {
                when (result) {
                    is State.Done -> it.copy(isLoading = false, isUserFound = result.data)
                    is State.Error -> {
                        val errorMessages = it.errorMessages + ErrorMessage(
                            id = UUID.randomUUID().mostSignificantBits, messageId = R.string.google_app_id
                        )
                        it.copy(isUserFound = null, errorMessages = errorMessages, isLoading = false)
                    }
                    else -> throw java.lang.IllegalStateException("")
                }
            }
        }
    }
}
