package matej.lamza.mycoach.ui.signin

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.FirebaseAuth
import matej.lamza.mycoach.common.composables.LoadingContent
import matej.lamza.mycoach.data.local.session.SessionProvider
import matej.lamza.mycoach.ui.google.GoogleOneTapSignIn
import org.koin.java.KoinJavaComponent.get

@Composable
fun SignInScreen(sessionProvider: SessionProvider, onSignInSuccess: (() -> Unit)) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Column {
            Text(text = "Welcome to sign in screen")
            GoogleOneTapSignIn(context = LocalContext.current,
                sessionProvider = sessionProvider,
                onSignInSuccess = onSignInSuccess,
                onSignInFailed = { Log.e("bbb", "SignInScreen: ${it.message} ", it) }
            )
        }
    }
}
