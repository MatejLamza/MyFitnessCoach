package matej.lamza.mycoach.navigation

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import matej.lamza.mycoach.ui.theme.Yellowish

@Composable
fun BottomNavBar(navController: NavHostController) {
    val screens = listOf(
        Screen.Home,
        Screen.Client,
        Screen.WorkoutPlan,
        Screen.Analytics,
    )

    BottomNavigation(modifier = Modifier.heightIn(min = 70.dp), backgroundColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { bottomItem ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = bottomItem.icon), contentDescription = bottomItem.title) },
                label = { Text(text = bottomItem.title, fontSize = 9.sp) },
                selectedContentColor = Yellowish,
                unselectedContentColor = Color.Black,
                alwaysShowLabel = true,
                selected = currentRoute == bottomItem.route,
                onClick = { navController.navigateSingleTopTo(bottomItem.route) }
            )
        }
    }
}
