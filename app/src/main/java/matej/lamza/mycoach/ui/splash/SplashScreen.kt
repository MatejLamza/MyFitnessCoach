package matej.lamza.mycoach.ui.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SplashScreen(
    uiState: SplashUIState,
    onNoSessionFound: (() -> Unit),
    onSessionFound: (() -> Unit)
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is splash screen")
        if (uiState.isLoading) CircularProgressIndicator(modifier = Modifier.size(60.dp))
        else {
            when (uiState) {
                is SplashUIState.UserNotFound -> onNoSessionFound.invoke()
                is SplashUIState.UserFound -> onSessionFound.invoke()
            }
        }
    }
}
