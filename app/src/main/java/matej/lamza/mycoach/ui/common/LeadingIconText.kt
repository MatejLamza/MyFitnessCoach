package matej.lamza.mycoach.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import matej.lamza.mycoach.ui.theme.Blackish
import matej.lamza.mycoach.ui.theme.Rubik

@Composable
fun LeadingIconText(vectorResource: Int, imageDescription: String? = null, text: String, textStyle: TextStyle? = null) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = vectorResource),
            contentDescription = imageDescription
        )
        Spacer(modifier = Modifier.width(5.dp))
        val style = textStyle ?: TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = Rubik,
            fontSize = 14.sp,
            color = Blackish
        )
        Text(text, style = style)
    }
}
