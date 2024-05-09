package care.intouch.app.core.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import care.intouch.app.core.navigation.Authentication
import care.intouch.app.core.navigation.BottomNav
import care.intouch.app.core.navigation.PasswordRecovery
import care.intouch.app.core.navigation.PinCodeConfirmation
import care.intouch.app.core.navigation.PinCodeEnter
import care.intouch.app.core.navigation.PinCodeInstallation
import care.intouch.app.core.navigation.Registration
import care.intouch.app.core.navigation.SendingNotification
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

        composable(route = Registration.route) {
            RegistrationScreen(
                goToPinCodeInstallation = {
                    navController.navigate(route = PinCodeInstallation.route)
                }
            )
        }

        composable(route = PinCodeInstallation.route) {
            PinCodeInstallationScreen(
                goToPinCodeConfirmationScreen = {
                    navController.navigate(route = PinCodeConfirmation.route)
                },
                goToHomeScreen = {
                    navController.navigate(route = BottomNav.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = PinCodeConfirmation.route) {
            PinCodeConfirmationScreen(
                goToHomeScreen = {
                    navController.navigate(route = BottomNav.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = PinCodeEnter.route) {
            EnterPinCodeScreen(
                goToHomeScreen = {
                    navController.navigate(route = BottomNav.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                goToAuthenticationScreen = {
                    navController.navigate(route = Authentication.route)
                }
            )
        }

        composable(route = Authentication.route) {
            AuthenticationScreen(
                goToPasswordRecoveryScreen = {
                    navController.navigate(route = PasswordRecovery.route)
                },
                goToPinCodeInstallation = {
                    navController.navigate(route = PinCodeInstallation.route)
                }
            )
        }

        composable(route = PasswordRecovery.route) {
            PasswordRecoveryScreen(
                goToSendingNotificationScreen = {
                    navController.navigate(route = SendingNotification.route)
                }
            )
        }

        composable(route = SendingNotification.route) {
            SendingNotificationScreen(
                goToAuthenticationScreen = {
                    navController.navigate(route = Authentication.route)
                }
            )
        }

        composable(route = BottomNav.route) {
            BottomNav()
        }
    }
}