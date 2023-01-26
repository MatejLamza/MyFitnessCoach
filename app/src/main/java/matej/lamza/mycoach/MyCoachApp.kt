package matej.lamza.mycoach

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import matej.lamza.mycoach.navigation.AppRoute
import matej.lamza.mycoach.navigation.MyCoachNavHost
import matej.lamza.mycoach.ui.home.HomeScreen
import matej.lamza.mycoach.ui.signin.SignInScreen
import matej.lamza.mycoach.ui.splash.SplashRoute
import matej.lamza.mycoach.ui.splash.SplashViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyCoachApp() {
    val navController = rememberNavController()

    MyCoachContent(navController = navController, splashViewModel = koinViewModel<SplashViewModel>())
}

@Composable
fun MyCoachContent(
    navController: NavHostController,
    splashViewModel: SplashViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
    ) {
        MyCoachNavHost(navController = navController)
    }
}



