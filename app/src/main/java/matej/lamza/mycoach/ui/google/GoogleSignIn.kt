package matej.lamza.mycoach.ui.google

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import kotlinx.coroutines.launch
import matej.lamza.mycoach.data.local.session.SessionProvider

private const val TAG = "GoogleOneTap"

@Composable
fun GoogleOneTapSignIn(
    context: Context,
    isAutoSignInEnabled: Boolean = false,
    onSignInSuccess: () -> Unit,
    onSignInFailed: (Throwable) -> Unit,
    sessionProvider: SessionProvider
) {
    val coroutineScope = rememberCoroutineScope()
    val oneTapClient = remember { Identity.getSignInClient(context) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
        val credentials = getIDTokenFromGoogle(it, oneTapClient)
        if (credentials != null) {
            coroutineScope.launch {
                authenticateWithFirebase(credentials, sessionProvider, onSignInSuccess, onFailure = onSignInFailed)
            }
        }
    }

    GoogleSignInButton {
        coroutineScope.launch {
            signIn(oneTapClient = oneTapClient, signInLauncher = launcher) {
                Log.e(TAG, "GoogleOneTapSignIn Error: ", it)
            }
        }
    }

    if (isAutoSignInEnabled) {
        val isAlreadyLaunched = rememberSaveable { mutableStateOf(false) }
        if (!isAlreadyLaunched.value) {
            LaunchedEffect(context) {
                signIn(
                    oneTapClient = oneTapClient,
                    signInLauncher = launcher,
                    onSignInFailed = { Log.e(TAG, "Failed signig in: ", it) }
                )
            }
        }
    }
}

private fun signIn(
    isAutoSignInEnabled: Boolean = true,
    oneTapClient: SignInClient,
    signInLauncher: ActivityResultLauncher<IntentSenderRequest>,
    onSignInFailed: (Throwable) -> Unit,
) {
    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId("71160016830-jmievthqgi14kgjh8shsej74d1002gkg.apps.googleusercontent.com")
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()

    oneTapClient.beginSignIn(signInRequest)
        .addOnSuccessListener { result ->
            runCatching { signInLauncher.launch(IntentSenderRequest.Builder(result.pendingIntent).build()) }
                .onFailure { onSignInFailed(it) }
        }
        .addOnFailureListener { error ->
            if (isAutoSignInEnabled) {
                signIn(
                    isAutoSignInEnabled = false,
                    oneTapClient = oneTapClient,
                    signInLauncher = signInLauncher,
                    onSignInFailed = { onSignInFailed(error) }
                )
            } else {
                onSignInFailed(error)
            }
        }
}

private fun getIDTokenFromGoogle(
    activityResult: ActivityResult,
    oneTapClient: SignInClient
): SignInCredential? {
    return if (activityResult.resultCode == Activity.RESULT_OK)
        oneTapClient.getSignInCredentialFromIntent(activityResult.data)
    else null
}

private suspend fun authenticateWithFirebase(
    signInCredentials: SignInCredential,
    sessionProvider: SessionProvider,
    onSuccess: (() -> Unit),
    onFailure: ((Throwable) -> Unit)
) {
    runCatching {
        sessionProvider.login(signInCredentials)
        onSuccess.invoke()
    }.onFailure(onFailure)
}

