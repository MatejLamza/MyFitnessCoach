package matej.lamza.mycoach.common.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun LoadingContentPullToRefresh(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    if (empty) emptyContent()
    else {
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.size(80.dp))
        } else {
            content()
        }
    }
}
