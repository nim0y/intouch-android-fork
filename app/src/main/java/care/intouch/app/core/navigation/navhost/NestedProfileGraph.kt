package care.intouch.app.core.navigation.navhost

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import care.intouch.app.core.navigation.MainNav
import care.intouch.app.core.navigation.PinCodeChange
import care.intouch.app.core.navigation.PinCodeConfirm
import care.intouch.app.core.navigation.ProfileRouteBranch
import care.intouch.app.core.navigation.SuccessfulPinCodeChange
import care.intouch.app.feature.authorization.presentation.ui.pinCode.PinCodeConfirmationScreen
import care.intouch.app.feature.authorization.presentation.ui.pinCode.PinCodeInstallationScreen
import care.intouch.app.feature.profile.presentation.ui.SuccessfulPinCodeChangeScreen

fun NavGraphBuilder.addNestedProfileGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = PinCodeChange.route, route = ProfileRouteBranch.route
    ) {

        composable(route = PinCodeChange.route) {
            PinCodeInstallationScreen(onSaveClick = { argument ->
                navController.navigate(route = PinCodeConfirm.route + "/$argument")
            }, onSkipClick = {
                navController.navigate(route = MainNav.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            route = PinCodeConfirm.route + "/{pinCodeInst}"
        ) { backStackEntry ->
            PinCodeConfirmationScreen(onSaveClick = {
                navController.navigate(route = SuccessfulPinCodeChange.route)
            }, onSkipClick = {
                navController.navigate(route = MainNav.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }, pinCodeInst = backStackEntry.arguments?.getString("pinCodeInst"))
        }

        composable(route = SuccessfulPinCodeChange.route) {
            SuccessfulPinCodeChangeScreen(onBackToHome = {
                navController.navigate(route = MainNav.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            })
        }
    }
}