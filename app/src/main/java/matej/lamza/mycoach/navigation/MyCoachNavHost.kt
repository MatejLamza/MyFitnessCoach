package matej.lamza.mycoach.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import matej.lamza.mycoach.ui.client.ClientRoute
import matej.lamza.mycoach.ui.client.ClientViewModel
import matej.lamza.mycoach.ui.home.HomeScreen
import matej.lamza.mycoach.ui.signin.LoginViewModel
import matej.lamza.mycoach.ui.signin.SignInRoute
import matej.lamza.mycoach.ui.splash.SplashRoute
import matej.lamza.mycoach.ui.splash.SplashViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyCoachNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(route = Screen.Splash.route) {
            SplashRoute(getViewModel<SplashViewModel>(),
                onSessionNotFound = { navController.navigateSingleTopTo(Screen.Login.route) },
                onSessionFound = { navController.navigateSingleTopTo(Screen.Home.route) }
            )
        }
        composable(route = Screen.Login.route) {
            val loginViewModel = koinViewModel<LoginViewModel>()
            SignInRoute(
                loginViewModel = loginViewModel,
                onLoginSuccess = { navController.navigateSingleTopTo(Screen.Home.route) },
            )
        }
        composable(route = Screen.Home.route) { HomeScreen() }
        composable(route = Screen.Client.route) {
            val clientViewModel = koinViewModel<ClientViewModel>()
            ClientRoute(clientViewModel = clientViewModel)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
            inclusive = true
        }
        // Avoid multiple copies of the same destination when reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
