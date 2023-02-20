package matej.lamza.mycoach.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import matej.lamza.mycoach.R
import matej.lamza.mycoach.ui.theme.*

private const val SPLASH = 1000L
private const val ANIMATION = 200L
private const val ANIMATION_SHORT = 100L
private const val WEIGHT = 3f

@Composable
fun HomeScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(Offwhite)
                .padding(20.dp)
        ) {
            var isAvatarVisible by remember { mutableStateOf(false) }
            var isNameVisible by remember { mutableStateOf(false) }
            var isMessageVisible by remember { mutableStateOf(false) }
            var isWorkoutVisible by remember { mutableStateOf(false) }
            var isChallengeVisible by remember { mutableStateOf(false) }
            var isClientVisible by remember { mutableStateOf(false) }

            LaunchedEffect(LocalContext.current) {
                delay(SPLASH)
                isAvatarVisible = !isAvatarVisible
                delay(ANIMATION_SHORT)
                isNameVisible = !isNameVisible
                delay(ANIMATION)
                isMessageVisible = !isMessageVisible
                delay(ANIMATION_SHORT)
                isChallengeVisible = !isChallengeVisible
                delay(ANIMATION)
                isWorkoutVisible = !isWorkoutVisible
                delay(ANIMATION)
                isClientVisible = !isClientVisible
            }

            AnimatedVisibility(visible = isAvatarVisible, enter = slideInVertically() + fadeIn()) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MyProfileAvatar()
                }

            }
            Spacer(Modifier.height(20.dp))
            AnimatedVisibility(visible = isNameVisible, enter = slideInHorizontally() + fadeIn()) {

                Text(
                    "Hello Cubasti,",
                    fontSize = 40.sp,
                    fontFamily = Rubik,
                    fontWeight = FontWeight.Thin,
                    color = Color.Black,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(visible = isMessageVisible, enter = slideInHorizontally() + fadeIn()) {
                Text(
                    "Ready for a challenge?",
                    fontSize = 14.sp,
                    fontFamily = Rubik,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedVisibility(visible = isChallengeVisible, enter = slideInVertically() + fadeIn()) {
                ChallengeTile()
            }
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedVisibility(visible = isWorkoutVisible, enter = slideInHorizontally() + fadeIn()) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "Your Workout",
                            fontSize = 20.sp,
                            fontFamily = Rubik,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            modifier = Modifier.weight(WEIGHT)
                        )

                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.arrow_next),
                            contentDescription = "go to workout",
                            modifier = Modifier.weight(1f),
                            tint = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    ExerciseTile(Yellowish, R.drawable.icon_dumbell, "Weight Lifting", "5 Sets")
                    Spacer(modifier = Modifier.height(10.dp))
                    ExerciseTile(Blueish, R.drawable.sport_jump_rope_svgrepo_com, "Rope Skipping", "1000")
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            AnimatedVisibility(visible = isClientVisible, enter = slideInVertically() + fadeIn()) {
                NextClientCard()
            }
        }
    }
}

@Composable
fun ChallengeTile() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Blueish,
        elevation = 0.dp,
        shape = RoundedCornerShape(30.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Blackish)

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.running_shoe),
                        contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            Column() {
                Text(
                    "New challenge!",
                    fontFamily = Rubik,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Blackish
                )
                Spacer(modifier = Modifier.height(10.dp))
                val temp = buildAnnotatedString {
                    pushStyle(
                        SpanStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Rubik,
                            color = Blackish
                        )
                    )
                    append("4000")
                    pushStyle(
                        SpanStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = Rubik,
                            color = Blackish
                        )
                    )
                    append(" Steps")
                    toAnnotatedString()
                }
                Text(temp)
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Offwhite)

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.arrow_next),
                        contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }

}

@Composable
fun ExerciseTile(color: Color, iconID: Int, text1: String, text2: String) {
    Card(
        backgroundColor = Color.Transparent,
        border = BorderStroke(2.dp, Pinkish),
        elevation = 0.dp,
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier.height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(color)

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = iconID),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text1,
                modifier = Modifier.weight(2f),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = Rubik,
                fontSize = 14.sp
            )
            Text(
                text2, fontFamily = Rubik,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.verified),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun MyProfileAvatar() {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(Color.Red)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.avatar),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
    }

}

@Composable
fun NextClientCard() {
    Card(
        elevation = 0.dp,
        backgroundColor = Yellowish,
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 250.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(PaddingValues(top = 40.dp, bottom = 20.dp, start = 20.dp, end = 20.dp))

        ) {
            Text(
                "Andy  \n Hansen",
                fontWeight = FontWeight.Bold,
                fontFamily = Rubik,
                fontSize = 20.sp,
                color = Blackish
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text("2pm - 3pm", fontWeight = FontWeight.Normal, fontFamily = Rubik, fontSize = 14.sp, color = Blackish)
            Column(modifier = Modifier.weight(2f), verticalArrangement = Arrangement.Bottom) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Blackish)
                        .padding(vertical = 10.dp, horizontal = 30.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Cardio",
                            fontSize = 14.sp,
                            fontFamily = Rubik,
                            fontWeight = FontWeight.Normal,
                            color = Color.White
                        )
                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun PreviewClientCard() {
    NextClientCard()
}

