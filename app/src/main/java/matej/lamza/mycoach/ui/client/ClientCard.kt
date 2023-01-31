package matej.lamza.mycoach.ui.client

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import matej.lamza.mycoach.R
import matej.lamza.mycoach.ui.theme.Rubik


@Composable
fun ClientCard() {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.client_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(verticalArrangement = Arrangement.Center) {
            ClientNameComponenet()
            Spacer(modifier = Modifier.height(30.dp))
            TimeSlotComponent()
            Spacer(modifier = Modifier.height(30.dp))
            WorkoutSummaryComponenet()
        }
    }
}

@Composable
private fun TimeSlotComponent() {
    OutlinedButton(
        onClick = {}, colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            "2pm - 3pm", color = Color.LightGray, modifier = Modifier.padding(10.dp), fontFamily = Rubik,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
private fun ClientNameComponenet() {
    val clientName = buildAnnotatedString {
        pushStyle(
            SpanStyle(
                color = Color.White,
                fontFamily = Rubik,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
        )
        append("Milica \n")
        append("Krmpotic")
        toAnnotatedString()
    }
    Text(clientName)
}

@Composable
private fun WorkoutSummaryComponenet() {
    Card(
        modifier = Modifier
            .height(120.dp)
            .width(80.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp)
        ) {
            Box(
                Modifier
                    .clip(CircleShape)
                    .size(30.dp)
                    .background(Color(183, 186, 140))
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.cardio_icon),
                    contentDescription = null,
                    modifier = Modifier.padding(3.dp)
                )
            }

            Text("Includes", fontWeight = FontWeight.Light, fontFamily = Rubik, fontSize = 10.sp)
            Text("5 Cardio Exercises", fontFamily = Rubik, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
fun ClientCardPreview() {
    ClientCard()
}
