package matej.lamza.mycoach.ui.signin

import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import matej.lamza.mycoach.ui.google.getIDTokenFromGoogle

@Composable
fun SignInRoute(
    uiState: LoginUIState,
    authWithFirebase: (SignInCredential) -> Unit,
    onSignInClick: (SignInClient, ActivityResultLauncher<IntentSenderRequest>) -> Unit,
    onLoginSuccess: () -> Unit,
    onErrorDismissed: (Long) -> Unit,
    getToken: ((ActivityResult, SignInClient) -> SignInCredential?)
) {
    SignInScreen(
        uiState = uiState,
        authWithFirebase = authWithFirebase,
        onSignInClick = onSignInClick,
        onLoginSuccess,
        onErrorDismissed,
        getToken
    )
}

@Composable
fun SignInRoute(
    loginViewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
) {
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    SignInRoute(
        uiState = uiState,
        authWithFirebase = { loginViewModel.authenticateWithFirebase(it) },
        onSignInClick = { client, request ->
            loginViewModel.signIn(oneTapClient = client, signInLauncher = request)
        },
        onLoginSuccess = onLoginSuccess,
        onErrorDismissed = { loginViewModel.errorShown(it) },
        getToken = { act, credential -> getIDTokenFromGoogle(act, credential) }
    )
}


