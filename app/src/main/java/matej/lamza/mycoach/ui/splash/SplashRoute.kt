package matej.lamza.mycoach.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun SplashRoute(splashViewModel: SplashViewModel, onSessionFound: () -> Unit, onSessionNotFound: () -> Unit) {
    val uiState by splashViewModel.uiState.collectAsStateWithLifecycle()
    SplashRoute(uiState = uiState, onSessionFound, onSessionNotFound)
}

@Composable
fun SplashRoute(
    uiState: SplashUIState,
    onSessionFound: (() -> Unit),
    onSessionNotFound: (() -> Unit)
) {
    SplashScreen(uiState = uiState, onSessionFound = onSessionFound, onNoSessionFound = onSessionNotFound)
}
