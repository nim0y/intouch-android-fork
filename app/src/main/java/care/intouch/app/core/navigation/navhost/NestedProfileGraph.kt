package care.intouch.app.core.navigation.navhost

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import care.intouch.app.core.navigation.NewPinCode
import care.intouch.app.core.navigation.PinCodeChange
import care.intouch.app.core.navigation.PinCodeConfirm
import care.intouch.app.core.navigation.ProfileRouteBranch
import care.intouch.app.core.navigation.SuccessfulPinCodeChange
import care.intouch.app.feature.profile.presentation.ui.NewPinCodeScreen
import care.intouch.app.feature.profile.presentation.ui.PinCodeChangeScreen
import care.intouch.app.feature.profile.presentation.ui.PinCodeConfirmationScreen
import care.intouch.app.feature.profile.presentation.ui.SuccessfulPinCodeChangeScreen

fun NavGraphBuilder.addNestedProfileGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = PinCodeChange.route,
        route = ProfileRouteBranch.route
    ) {

        composable(route = PinCodeChange.route) {
            PinCodeChangeScreen(
                goToNewPinCodeScreen = {
                    navController.navigate(route = NewPinCode.route)
                }
            )
        }

        composable(route = NewPinCode.route) {
            NewPinCodeScreen(
                goToPinCodeConfirmationScreen = {
                    navController.navigate(route = PinCodeConfirm.route)
                }
            )
        }

        composable(route = PinCodeConfirm.route) {
            PinCodeConfirmationScreen(
                goToSuccessfulPinCodeChangeScreen = {
                    navController.navigate(route = SuccessfulPinCodeChange.route)
                }
            )
        }

        composable(route = SuccessfulPinCodeChange.route) {
            SuccessfulPinCodeChangeScreen()
        }
    }
}