package care.intouch.app.core.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import care.intouch.app.core.navigation.Route
import care.intouch.app.feature.diary.presentation.ui.DiaryNoteScreen
import care.intouch.app.feature.home.presentation.ui.HomeScreen
import care.intouch.app.feature.plan.presentation.ui.PlanScreen
import care.intouch.app.feature.profile.presentation.ui.PasswordChangeScreen
import care.intouch.app.feature.profile.presentation.ui.ProfileScreen

@Composable
fun BottomNavHost(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,

        ) {

        composable(route = Route.HOME) {
            HomeScreen()
        }

        composable(route = Route.PLAN) {
            PlanScreen(
                goToTaskScreen = {
                    navController.navigate(route = Route.PLAN_ROUTE_BRANCH)
                }
            )
        }

        composable(route = Route.DIARY) {
            DiaryNoteScreen(
                goToCreatingNoteIntroductionScreen = {
                    navController.navigate(route = Route.DIARY_ROUTE_BRANCH)
                }
            )
        }

        composable(route = Route.PROFILE) {
            ProfileScreen(
                goToPasswordChangeScreen = {
                    navController.navigate(route = Route.PASSWORD_CHANGE)
                },
                goToPinCodeChangeScreen = {
                    navController.navigate(route = Route.PROFILE_ROUTE_BRANCH)
                }
            )
        }

        composable(route = Route.PASSWORD_CHANGE) {
            PasswordChangeScreen()
        }

        composable(route = Route.PLAN_ROUTE_BRANCH) {
            PlanNavHost(
                startDestination = Route.TASK
            )
        }

        composable(route = Route.DIARY_ROUTE_BRANCH) {
            DiaryNavHost(
                startDestination = Route.CREATING_NOTE_INTRODUCTION
            )
        }

        composable(route = Route.PROFILE_ROUTE_BRANCH) {
            ProfileNavHost(
                startDestination = Route.PIN_CODE_CHANGE
            )
        }
    }
}


