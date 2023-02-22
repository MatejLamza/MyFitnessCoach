package matej.lamza.mycoach.ui.client

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import matej.lamza.mycoach.R


@Composable
fun AddNewClientScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painterResource(id = R.drawable.add_client_bg),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.weight(2f)
            )
            Box(modifier = Modifier.weight(2f)) {

            }
        }
    }
}


@Preview
@Composable
fun AddNewClientScreenPreview() {
    AddNewClientScreen()
}


