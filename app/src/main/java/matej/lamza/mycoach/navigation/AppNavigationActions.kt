package matej.lamza.mycoach.navigation

sealed class Screen(val route: String) {
    object Splash : Screen(route = "splash_screen")
    object Home : Screen(route = "home_screen")
    object Login : Screen(route = "login_screen")
}
