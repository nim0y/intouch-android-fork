package care.intouch.app.core.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import care.intouch.app.core.navigation.Route
import care.intouch.app.feature.profile.presentation.ui.NewPinCodeScreen
import care.intouch.app.feature.profile.presentation.ui.PinCodeChangeScreen
import care.intouch.app.feature.profile.presentation.ui.PinCodeConfirmationScreen
import care.intouch.app.feature.profile.presentation.ui.SuccessfulPinCodeChangeScreen

@Composable
fun ProfileNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(route = Route.PIN_CODE_CHANGE) {
            PinCodeChangeScreen(
                goToNewPinCodeScreen = {
                    navController.navigate(route = Route.NEW_PIN_CODE)
                }
            )
        }

        composable(route = Route.NEW_PIN_CODE) {
            NewPinCodeScreen(
                goToPinCodeConfirmationScreen = {
                    navController.navigate(route = Route.PIN_CODE_CONFIRM)
                }
            )
        }

        composable(route = Route.PIN_CODE_CONFIRM) {
            PinCodeConfirmationScreen(
                goToSuccessfulPinCodeChangeScreen = {
                    navController.navigate(route = Route.SUCCESSFUL_PIN_CODE_CHANGE)
                }
            )
        }

        composable(route = Route.SUCCESSFUL_PIN_CODE_CHANGE) {
            SuccessfulPinCodeChangeScreen()
        }
    }
}