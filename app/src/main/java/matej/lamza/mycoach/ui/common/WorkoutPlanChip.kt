package matej.lamza.mycoach.ui.common

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import matej.lamza.mycoach.data.local.enums.WorkoutPlan
import matej.lamza.mycoach.ui.theme.Typography


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WorkoutPlanChip(workoutPlan: WorkoutPlan) {
    Chip(modifier = Modifier.widthIn(32.dp), onClick = { }) {
        Text(text = workoutPlan.name.uppercase(), style = Typography.body2)
    }
}
