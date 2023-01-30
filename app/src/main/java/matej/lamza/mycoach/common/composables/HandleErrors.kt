package matej.lamza.mycoach.common.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import matej.lamza.mycoach.common.state.UiState

@Composable
fun UIErrorHandler(
    context: Context,
    uiState: UiState,
    onErrorDismissed: (Long) -> Unit,
) {
    if (uiState.errorMessages.isNotEmpty()) {
        val errorMessage = remember(uiState) { uiState.errorMessages[0] }
        val errorMessageText = stringResource(id = errorMessage.messageId)
        val onErrorDismissState by rememberUpdatedState(onErrorDismissed)

        LaunchedEffect(errorMessageText) {
            Toast.makeText(context, errorMessageText, Toast.LENGTH_LONG).show()
            onErrorDismissState(errorMessage.id)
        }
    }
}
