package care.intouch.app.core.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import care.intouch.app.core.navigation.BottomNav
import care.intouch.app.core.navigation.Route
import care.intouch.app.feature.authorization.presentation.ui.AuthenticationScreen
import care.intouch.app.feature.authorization.presentation.ui.EnterPinCodeScreen
import care.intouch.app.feature.authorization.presentation.ui.PasswordRecoveryScreen
import care.intouch.app.feature.authorization.presentation.ui.PinCodeConfirmationScreen
import care.intouch.app.feature.authorization.presentation.ui.PinCodeInstallationScreen
import care.intouch.app.feature.authorization.presentation.ui.RegistrationScreen
import care.intouch.app.feature.authorization.presentation.ui.SendingNotificationScreen

@Composable
fun AuthorizationNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(route = Route.REGISTRATION) {
            RegistrationScreen(
                goToPinCodeInstallation = {
                    navController.navigate(route = Route.PIN_CODE_INSTALLATION)
                }
            )
        }

        composable(route = Route.PIN_CODE_INSTALLATION) {
            PinCodeInstallationScreen(
                goToPinCodeConfirmationScreen = {
                    navController.navigate(route = Route.PIN_CODE_CONFIRMATION)
                },
                goToHomeScreen = {
                    navController.navigate(route = Route.BOTTOM_NAV)
                }
            )
        }

        composable(route = Route.PIN_CODE_CONFIRMATION) {
            PinCodeConfirmationScreen(
                goToHomeScreen = {
                    navController.navigate(route = Route.BOTTOM_NAV)
                }
            )
        }

        composable(route = Route.PIN_CODE_ENTER) {
            EnterPinCodeScreen(
                goToHomeScreen = {
                    navController.navigate(route = Route.BOTTOM_NAV)
                },
                goToAuthenticationScreen = {
                    navController.navigate(route = Route.AUTHENTICATION)
                }
            )
        }

        composable(route = Route.AUTHENTICATION) {
            AuthenticationScreen(
                goToPasswordRecoveryScreen = {
                    navController.navigate(route = Route.PASSWORD_RECOVERY)
                },
                goToPinCodeInstallation = {
                    navController.navigate(route = Route.PIN_CODE_INSTALLATION)
                }
            )
        }

        composable(route = Route.PASSWORD_RECOVERY) {
            PasswordRecoveryScreen(
                goToSendingNotificationScreen = {
                    navController.navigate(route = Route.SENDING_NOTIFICATION)
                }
            )
        }

        composable(route = Route.SENDING_NOTIFICATION) {
            SendingNotificationScreen(
                goToAuthenticationScreen = {
                    navController.navigate(route = Route.AUTHENTICATION)
                }
            )
        }

        composable(route = Route.BOTTOM_NAV) {
            BottomNav()
        }
    }
}