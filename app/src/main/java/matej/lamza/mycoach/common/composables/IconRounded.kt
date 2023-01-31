package matej.lamza.mycoach.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun IconRounded(
    vectorResourceID: Int,
    size: Dp = 30.dp,
    backgroundColor: Color = Color(183, 186, 140),
) {
    Box(
        Modifier
            .clip(CircleShape)
            .size(size)
            .background(backgroundColor)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = vectorResourceID),
            contentDescription = null,
            modifier = Modifier.padding(3.dp)
        )
    }
}

// todo: Add function for drawable images as well
