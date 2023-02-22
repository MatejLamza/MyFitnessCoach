package matej.lamza.mycoach.ui.client

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import matej.lamza.mycoach.R
import matej.lamza.mycoach.common.composables.SearchAppBar
import matej.lamza.mycoach.data.local.Client
import matej.lamza.mycoach.data.local.enums.SearchWidgetState
import matej.lamza.mycoach.data.local.enums.WorkoutPlan
import matej.lamza.mycoach.ui.common.LeadingIconText
import matej.lamza.mycoach.ui.common.ProfileIcon
import matej.lamza.mycoach.ui.common.WorkoutPlanChip
import matej.lamza.mycoach.ui.theme.*

private const val TAG = "ClientScreen"
private val paddingValues =
    PaddingValues(top = PADDING_LARGE, bottom = PADDING_DEFAULT, start = PADDING_DEFAULT, end = PADDING_DEFAULT)


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClientScreen(
    clients: List<Client>,
    searchText: String,
    searchWidgetState: SearchWidgetState,
    onSearchTrigger: () -> Unit,
    onTextChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSearch: (String) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            searchText = searchText,
            searchWidgetState = searchWidgetState,
            onSearchTrigger = {
                Log.d(TAG, "ClientScreen: on search ")
                onSearchTrigger.invoke()
            },
            onTextChange = onTextChange,
            onCloseClick = onCloseClick,
            onSearch = { onSearch.invoke(it) }
        )
    }) { _ ->
        Column(modifier = Modifier.padding(PADDING_DEFAULT)) {
            Text("Upcoming client ", style = Typography.body1.copy(fontWeight = FontWeight.Thin))
            Spacer(modifier = Modifier.height(4.dp))
            UpcomingEventCard()
            Divider()
            Spacer(modifier = Modifier.height(PADDING_DEFAULT))

            LazyColumn {
                items(clients) { client ->
                    ClientCard(client = client)
                    Spacer(modifier = Modifier.height(PADDING_SMALL))
                }
            }
        }
    }
}

@Composable
internal fun UpcomingEventCard(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
) {
    Card(
        modifier = modifier.heightIn(max = CARD_HEIGHT_SMALL),
        elevation = 0.dp,
        backgroundColor = Yellowish,
        shape = RoundedCornerShapeLarge,
    ) {
        Column(modifier = modifier) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileIcon(size = PROFILE_ICON_SIZE_DEFAULT)
                Spacer(modifier = Modifier.width(PADDING_SMALL))
                Text("Milica Krmpotic", style = Typography.body1)
            }
            Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
                LeadingIconText(vectorResource = R.drawable.baseline_access_time_24, text = "13:00-14:00")
                LeadingIconText(vectorResource = R.drawable.baseline_location_24, text = "Gyms4You")
            }
        }
    }
}

@Composable
internal fun TimeAndLocationInformation() {
    Column {
        Spacer(modifier = Modifier.height(PADDING_DEFAULT))
        LeadingIconText(vectorResource = R.drawable.baseline_access_time_24, text = "13:00-14:00")
        Spacer(modifier = Modifier.height(PADDING_SMALL))
        LeadingIconText(vectorResource = R.drawable.baseline_location_24, text = "Play Fitness")
    }
}

@Composable
internal fun ClientCard(client: Client) {
    Card(
        elevation = 0.dp,
        backgroundColor = Yellowish,
        shape = RoundedCornerShapeLarge,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = DEFAULT_CARD_HEIGHT)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("${client.name} \n ${client.lastName}", style = Typography.body1)
                    ProfileIcon(size = PROFILE_ICON_SIZE_DEFAULT)
                }
                TimeAndLocationInformation()
            }
            WorkoutPlanChip(workoutPlan = WorkoutPlan.LEAN)
        }
    }
}

@Composable
fun TopAppBar(
    searchText: String,
    searchWidgetState: SearchWidgetState,
    onSearchTrigger: () -> Unit,
    onTextChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSearch: (String) -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> DefaultAppBar(onSearchClicked = onSearchTrigger)
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchText,
                onTextChange = onTextChange,
                onClose = onCloseClick,
                onSearchClicked = onSearch
            )
        }
    }
}

@Composable
internal fun DefaultAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(title = {
        Text(
            text = "Clients",
            style = Typography.body2.copy(color = White, fontWeight = FontWeight.Thin, fontSize = 24.sp)
        )
    }, actions = {
        IconButton(onClick = { onSearchClicked() }) { SearchIcon() }
    })
}
