package matej.lamza.mycoach.ui.splash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import matej.lamza.mycoach.R
import matej.lamza.mycoach.common.composables.LoadingContent
import matej.lamza.mycoach.common.composables.LoadingContentPullToRefresh


@Composable
fun SplashScreen(
    uiState: SplashUIState,
    onNoSessionFound: (() -> Unit),
    onSessionFound: (() -> Unit)
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingContent(
                empty = when (uiState) {
                    is SplashUIState.UserFound -> false
                    is SplashUIState.UserNotFound -> uiState.isLoading
                },
                isLoading = uiState.isLoading,
                emptyContent = { onNoSessionFound.invoke() },
                content = { onSessionFound.invoke() })
        }
    }
}
