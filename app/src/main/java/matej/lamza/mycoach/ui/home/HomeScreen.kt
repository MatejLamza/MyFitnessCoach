package matej.lamza.mycoach.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import matej.lamza.mycoach.ui.client.ClientCard
import uz.yusufbekibragimov.swipecard.CardShadowSide
import uz.yusufbekibragimov.swipecard.SwipeCard
import java.util.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .background(Color.Gray)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SwipeCard(
                itemsList = mutableListOf<Int>(1, 2, 3),
                shadowSide = CardShadowSide.ShadowTop,
                orientation = Orientation.Vertical,
                heightCard = 600.dp
            ) {
                ClientCard()
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
