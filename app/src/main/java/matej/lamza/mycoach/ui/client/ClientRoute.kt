package matej.lamza.mycoach.ui.client

import androidx.compose.runtime.Composable
import matej.lamza.mycoach.data.local.Client
import matej.lamza.mycoach.data.local.enums.Gender
import java.util.*


@Composable
fun ClientRoute(onAddClientClick: () -> Unit) {
    ClientScreen {
        onAddClientClick.invoke()
    }
}

@Composable
fun ClientRoute(clientViewModel: ClientViewModel) {
    ClientRoute {
        clientViewModel.saveClient(
            Client(
                UUID.randomUUID().mostSignificantBits.toString(),
                "Smiljanja",
                "Kekin",
                "1997",
                Gender.FEMALE
            )
        )
    }
}
