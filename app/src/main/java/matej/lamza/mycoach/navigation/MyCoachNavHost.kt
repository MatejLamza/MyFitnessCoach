package matej.lamza.mycoach.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import matej.lamza.mycoach.ui.home.HomeScreen
import matej.lamza.mycoach.ui.signin.LoginViewModel
import matej.lamza.mycoach.ui.signin.SignInRoute
import matej.lamza.mycoach.ui.splash.SplashRoute
import matej.lamza.mycoach.ui.splash.SplashViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyCoachNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppRoute.SPLASH
    ) {
        composable(AppRoute.SPLASH) {
            val splashViewModel = koinViewModel<SplashViewModel>()
            SplashRoute(splashViewModel,
                onSessionNotFound = { navController.navigate(AppRoute.SIGN_IN) { launchSingleTop = true } },
                onSessionFound = { navController.navigate(AppRoute.HOME) { launchSingleTop = true } }
            )
        }
        composable(AppRoute.SIGN_IN) {
            val loginViewModel = koinViewModel<LoginViewModel>()
            SignInRoute(
                loginViewModel = loginViewModel,
                onLoginSuccess = { navController.navigate(AppRoute.HOME) { launchSingleTop = true } },
            )
        }
        composable(AppRoute.HOME) { HomeScreen() }
    }
}
