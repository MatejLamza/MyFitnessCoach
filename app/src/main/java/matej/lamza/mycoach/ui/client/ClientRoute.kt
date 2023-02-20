package matej.lamza.mycoach.ui.client

import androidx.compose.runtime.Composable
import matej.lamza.mycoach.data.local.Client


@Composable
fun ClientRoute(onAddClientClick: () -> Unit) {
    ClientScreen {
        onAddClientClick.invoke()
    }
}

@Composable
fun ClientRoute(clientViewModel: ClientViewModel) {
    ClientRoute {
        clientViewModel.addUserToFirestore(Client("Milica", "Krmpotic", "1997"))
    }
}
