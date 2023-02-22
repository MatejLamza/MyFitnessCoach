package matej.lamza.mycoach.ui.client

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import matej.lamza.mycoach.data.local.Client
import matej.lamza.mycoach.data.local.enums.SearchWidgetState


@Composable
fun ClientRoute(
    clients: List<Client>,
    searchText: String,
    searchWidgetState: SearchWidgetState,
    onSearchTrigger: () -> Unit,
    onTextChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSearch: (String) -> Unit
) {
    ClientScreen(
        clients,
        searchText = searchText,
        searchWidgetState = searchWidgetState,
        onSearchTrigger = onSearchTrigger,
        onTextChange = onTextChange,
        onCloseClick = onCloseClick,
        onSearch = onSearch
    )
}

@Composable
fun ClientRoute(clientViewModel: ClientViewModel) {
    val searchText by clientViewModel.searchTextState.collectAsStateWithLifecycle()
    val clients by clientViewModel.clients.collectAsStateWithLifecycle()
    ClientRoute(
        clients,
        searchText = searchText,
        searchWidgetState = clientViewModel.searchWidgetState.value,
        onTextChange = { clientViewModel.updateSearchTextState(newValue = it) },
        onSearchTrigger = { clientViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED) },
        onCloseClick = { clientViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED) },
        onSearch = { Log.d("bbb", "Searching: $it ") }
    )
}
