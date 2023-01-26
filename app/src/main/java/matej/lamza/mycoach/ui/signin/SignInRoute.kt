package matej.lamza.mycoach.ui.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.android.gms.auth.api.identity.SignInCredential
import matej.lamza.mycoach.data.local.session.SessionProvider
import matej.lamza.mycoach.ui.splash.SplashScreen
import matej.lamza.mycoach.ui.splash.SplashUIState
import matej.lamza.mycoach.ui.splash.SplashViewModel

@Composable
fun SignInRoute(sessionProvider: SessionProvider,onSignInSuccess: (() -> Unit)) {
    SignInScreen(sessionProvider = sessionProvider,onSignInSuccess = onSignInSuccess)
}


