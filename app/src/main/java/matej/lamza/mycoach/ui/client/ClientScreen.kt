package matej.lamza.mycoach.ui.client

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import matej.lamza.mycoach.R
import matej.lamza.mycoach.data.local.enums.WorkoutPlan
import matej.lamza.mycoach.ui.common.LeadingIconText
import matej.lamza.mycoach.ui.common.ProfileIcon
import matej.lamza.mycoach.ui.common.WorkoutPlanChip
import matej.lamza.mycoach.ui.theme.*

private const val TAG = "ClientScreen"
private val paddingValues = PaddingValues(
    top = PADDING_LARGE,
    bottom = PADDING_DEFAULT,
    start = PADDING_DEFAULT,
    end = PADDING_DEFAULT
)

@Composable
fun ClientScreen(
    onAddClientClick: () -> Unit
) {
    LazyColumn {
        items(5) {
            ClientCard()
        }
    }
}

@Composable
private fun TimeAndLocationInformation() {
    Column {
        Spacer(modifier = Modifier.height(PADDING_DEFAULT))
        LeadingIconText(vectorResource = R.drawable.baseline_access_time_24, text = "13:00-14:00")
        Spacer(modifier = Modifier.height(PADDING_SMALL))
        LeadingIconText(vectorResource = R.drawable.baseline_location_24, text = "Play Fitness")
    }
}

@Composable
private fun ClientCard() {
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
                    Text("Milica \n Krmpotic", style = Typography.body1)
                    ProfileIcon(size = PROFILE_ICON_SIZE_DEFAULT)
                }
                TimeAndLocationInformation()
            }
            WorkoutPlanChip(workoutPlan = WorkoutPlan.LEAN)
        }
    }
}

@Preview
@Composable
private fun ClientScreenPreview() {
    ClientScreen {
    }
}

@Preview
@Composable
private fun ClientCardPreview() {
    ClientCard()
}




