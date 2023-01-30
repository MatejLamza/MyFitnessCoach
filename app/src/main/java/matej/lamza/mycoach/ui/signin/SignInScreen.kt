package matej.lamza.mycoach.ui.signin

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import matej.lamza.mycoach.ui.google.GoogleSignInButton

@Composable
fun SignInScreen(
    uiState: LoginUIState,
    authWithFirebase: (SignInCredential) -> Unit,
    onSignInClick: (SignInClient, ActivityResultLauncher<IntentSenderRequest>) -> Unit,
    onLoginSuccess: (() -> Unit),
    onErrorDismissed: (Long) -> Unit,
    getToken: ((ActivityResult, SignInClient) -> SignInCredential?)
) {

    Log.d("SignInScreen", "SignInScreen: STATE: $uiState ")
    val context = LocalContext.current
    val oneTapClient = remember { Identity.getSignInClient(context) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
        val credentials = getToken(it, oneTapClient)
        if (credentials != null) {
            authWithFirebase.invoke(credentials)
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

        handleErrors(
            context = LocalContext.current,
            uiState = uiState,
            onErrorDismissed = onErrorDismissed,
        )
    }
}


@Composable
private fun handleErrors(
    context: Context,
    uiState: LoginUIState,
    onErrorDismissed: (Long) -> Unit,
) {
    Log.d("LoginGoogle", "handleErrors: ${uiState.errorMessages} ")
    if (uiState.errorMessages.isNotEmpty()) {
        val errorMessage = remember(uiState) { uiState.errorMessages[0] }
        val errorMessageText = stringResource(id = errorMessage.messageId)
        // If onRefreshPosts or onErrorDismiss change while the LaunchedEffect is running,
        // don't restart the effect and use the latest lambda values.
        val onErrorDismissState by rememberUpdatedState(onErrorDismissed)

        LaunchedEffect(errorMessageText) {
            Toast.makeText(context, errorMessageText, Toast.LENGTH_LONG).show()
            onErrorDismissState(errorMessage.id)
        }
    }
}
