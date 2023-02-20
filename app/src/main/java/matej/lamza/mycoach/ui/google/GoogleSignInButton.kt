package matej.lamza.mycoach.ui.google

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import matej.lamza.mycoach.R

private const val WEIGHT = 3f

@Composable
fun GoogleSignInButton(onClick: (() -> Unit)) {
    Button(
        modifier = Modifier.defaultMinSize(minHeight = 40.dp, minWidth = 300.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick
    ) {
        Row {
            Image(
                modifier = Modifier.weight(1f),
                imageVector = ImageVector.vectorResource(id = R.drawable.logo_googleg_48dp),
                contentDescription = null
            )
            Text("Continue with google", Modifier.weight(WEIGHT))
        }
    }
}

@Preview
@Composable
private fun GoogleSignInButtonPreview() {
    GoogleSignInButton {}
}
