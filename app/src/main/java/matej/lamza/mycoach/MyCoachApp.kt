package matej.lamza.mycoach

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
    ) {
        MyCoachNavHost(navController = navController)
    }
}

