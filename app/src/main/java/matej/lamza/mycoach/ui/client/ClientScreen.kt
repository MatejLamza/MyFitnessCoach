package matej.lamza.mycoach.ui.client

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import matej.lamza.mycoach.ui.theme.Blueish

private const val TAG = "ClientScreen"

@Composable
fun ClientScreen(
    onAddClientClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Blueish)
    ) {
        FloatingActionButton(onClick = {
            Log.d(TAG, "ClientScreen: adding client ...")
            onAddClientClick.invoke()
        }) {

        }
    }
}
