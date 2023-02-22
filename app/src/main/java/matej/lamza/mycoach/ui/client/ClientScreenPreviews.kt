package matej.lamza.mycoach.ui.client

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import matej.lamza.mycoach.common.composables.SearchAppBar
import matej.lamza.mycoach.data.local.enums.SearchWidgetState
import matej.lamza.mycoach.utils.Mock


@Preview
@Composable
fun ClientScreenPreview() {
    ClientScreen(
        clients = Mock.clients,
        searchText = "",
        searchWidgetState = SearchWidgetState.CLOSED,
        onSearchTrigger = { /*TODO*/ },
        onTextChange = {},
        onCloseClick = { /*TODO*/ },
        onSearch = {}
    )
}

@Preview
@Composable
private fun SearchAppBarPreview() {
    SearchAppBar(text = "", onTextChange = {}, onClose = { /*TODO*/ }, onSearchClicked = {})
}

@Preview
@Composable
private fun ClientCardPreview() {
    ClientCard(Mock.clients.first())
}

@Preview
@Composable
fun UpcomingEventCardPreview() {
    UpcomingEventCard()
}
