package matej.lamza.mycoach

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import matej.lamza.mycoach.navigation.BottomNavBar
import matej.lamza.mycoach.navigation.MyCoachNavHost
import matej.lamza.mycoach.ui.theme.MyCoachTheme

@Composable
fun MyCoachApp() {
    MyCoachTheme() {
        val navController = rememberNavController()
        MyCoachContent(navController = navController)
    }
}

@Composable
fun MyCoachContent(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) }
    ) { _ ->
        MyCoachNavHost(navController = navController)
    }
}

