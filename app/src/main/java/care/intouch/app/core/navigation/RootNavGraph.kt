package care.intouch.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import care.intouch.app.feature.authorization.presentation.ui.AuthenticationScreen
import care.intouch.app.feature.authorization.presentation.ui.PasswordRecoveryScreen
import care.intouch.app.feature.authorization.presentation.ui.PinCodeConfirmationScreen
import care.intouch.app.feature.authorization.presentation.ui.PinCodeInstallationScreen
import care.intouch.app.feature.authorization.presentation.ui.RegistrationScreen
import care.intouch.app.feature.authorization.presentation.ui.SendingNotificationScreen
import care.intouch.app.feature.diary.presentation.ui.DiaryNoteScreen
import care.intouch.app.feature.home.presentation.ui.HomeScreen
import care.intouch.app.feature.plan.presentation.ui.PlanScreen
import care.intouch.app.feature.plan.presentation.ui.TaskCompletingScreen
import care.intouch.app.feature.plan.presentation.ui.TaskEstimateScreen
import care.intouch.app.feature.plan.presentation.ui.TaskIntroductionScreen
import care.intouch.app.feature.plan.presentation.ui.TaskScreen
import care.intouch.app.feature.profile.presentation.ui.ProfileScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // Authorization
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

        //Bottom Navigation
        composable(route = Route.BOTTOM_NAV) {
            BottomNav()
        }

        composable(route = Route.HOME) {
            HomeScreen()
        }

        composable(route = Route.PLAN) {
            PlanScreen(
                goToTaskScreen = {
                    navController.navigate(route = Route.TASK)
                }
            )
        }

        composable(route = Route.DIARY) {
            DiaryNoteScreen(
                goToCreatingNoteIntroductionScreen = {}
            )
        }

        composable(route = Route.PROFILE) {
            ProfileScreen(
                goToPasswordChangeScreen = {},
                goToPinCodeChangeScreen = {}
            )
        }

        // Plan Graph Branch
        composable(route = Route.TASK) {
            TaskScreen(
                goToTaskIntroductionScreen = {
                    navController.navigate(route = Route.TASK_INTRODUCTION)
                }
            )
        }

        composable(route = Route.TASK_INTRODUCTION) {
            TaskIntroductionScreen(
                goToTaskCompletingScreen = {
                    navController.navigate(route = Route.TASK_COMPLETING)
                }
            )
        }

        composable(route = Route.TASK_ESTIMATE) {
            TaskEstimateScreen()
        }

        composable(route = Route.TASK_COMPLETING) {
            TaskCompletingScreen(
                goToTaskEstimateScreen = {
                    navController.navigate(route = Route.TASK_ESTIMATE)
                }
            )
        }
    }
}