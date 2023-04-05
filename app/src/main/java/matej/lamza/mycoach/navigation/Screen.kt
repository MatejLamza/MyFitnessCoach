package matej.lamza.mycoach.navigation

import matej.lamza.mycoach.R

sealed class Screen(val title: String, val route: String, val icon: Int = R.drawable.icon_dumbell) {
    object Splash : Screen("Splash", route = "splash_screen")
    object Home : Screen("Home", route = "home_screen")
    object Login : Screen("Login", route = "login_screen")
    object Client : Screen("Client", route = "client_screen")
    object WorkoutPlan : Screen("Workout Plan", route = "workout_plan")
    object Analytics : Screen("Analytics", route = "Analytics")

    companion object {

        val Screen.name
            get() =
                when (this) {
                    is Splash -> "Splash"
                    is Home -> "Home"
                    is Login -> "Login"
                    is Client -> "Clients"
                    is WorkoutPlan -> "Workout Plans"
                    is Analytics -> "Analytics"
                }
    }
}

