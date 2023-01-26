package matej.lamza.mycoach.ui.google

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import matej.lamza.mycoach.data.local.session.SessionProvider
import matej.lamza.mycoach.data.toDomainUser

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
  /*      handleGoogleOneTapResult(
            oneTapClient = oneTapClient,
            activityResult = it,
            sessionProvider,
            onSignInSuccess = onSignInSuccess,
            onSignInFailed = onSignInFailed,
        )*/
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
            runCatching {
                signInLauncher.launch(IntentSenderRequest.Builder(result.pendingIntent).build())
            }.onFailure {
                onSignInFailed(it)
            }
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
) {
    if (activityResult.resultCode == Activity.RESULT_OK) {
        val googleCredentials = oneTapClient.getSignInCredentialFromIntent(activityResult.data)
    }
}


private fun handleGoogleOneTapResult(
    oneTapClient: SignInClient,
    activityResult: ActivityResult,
    sessionProvider: SessionProvider,
    onSignInSuccess: (() -> Unit),
    onSignInFailed: ((Throwable) -> Unit)
) {
    val firebaseAuth = FirebaseAuth.getInstance()
    if (activityResult.resultCode == Activity.RESULT_OK)
        runCatching {
            val googleCredentials = oneTapClient.getSignInCredentialFromIntent(activityResult.data)
            val firebaseCredentials = GoogleAuthProvider.getCredential(googleCredentials.googleIdToken, null)
            firebaseAuth.signInWithCredential(firebaseCredentials)
                .addOnSuccessListener { onSignInSuccess.invoke() }
                .addOnFailureListener { Log.e(SessionProvider.TAG, "Failure: $it ", it); throw it }
        }.onFailure { onSignInFailed(it) }
    else
        onSignInFailed(IllegalStateException("Error happened in activity result!"))

}
