package matej.lamza.mycoach.ui.signin

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import matej.lamza.mycoach.common.composables.UIErrorHandler
import matej.lamza.mycoach.ui.google.GoogleSignInButton

@Composable
fun SignInScreen(
    uiState: LoginUIState,
    onFirebaseAuth: (SignInCredential) -> Unit,
    onSignInClick: (SignInClient, ActivityResultLauncher<IntentSenderRequest>) -> Unit,
    onLoginSuccess: (() -> Unit),
    onErrorDismiss: (Long) -> Unit,
    getToken: ((ActivityResult, SignInClient) -> SignInCredential?)
) {

    val context = LocalContext.current
    val oneTapClient = remember { Identity.getSignInClient(context) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
        val credentials = getToken(it, oneTapClient)
        if (credentials != null) {
            onFirebaseAuth.invoke(credentials)
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Column {
            Text(text = "Welcome to sign in screen")
            GoogleSignInButton { onSignInClick(oneTapClient, launcher) }
            when (uiState) {
                is LoginUIState.LoginSuccess -> onLoginSuccess.invoke()
                is LoginUIState.LoginFailure -> {}
            }
        }

        UIErrorHandler(
            context = LocalContext.current,
            uiState = uiState,
            onErrorDismiss = onErrorDismiss,
        )
    }
}
