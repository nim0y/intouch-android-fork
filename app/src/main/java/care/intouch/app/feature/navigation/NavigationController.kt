package care.intouch.app.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import care.intouch.app.feature.registration.presentation.ui.RegistrationScreen

@Composable
fun NavigationController(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = "startScreen"
        ) {

        }
        composable(
            route = NavigationDestination.Registration.destination,
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://app.intouch.care/activate-client/{clientId}/{token}/"
            })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("clientId")
            val token = backStackEntry.arguments?.getString("token")
            RegistrationScreen(
                userId,
                token
            )
        }
    }
}