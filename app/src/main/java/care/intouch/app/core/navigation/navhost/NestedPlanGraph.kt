package care.intouch.app.core.navigation.navhost

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import care.intouch.app.core.navigation.PlanRouteBranch
import care.intouch.app.core.navigation.Task
import care.intouch.app.core.navigation.TaskCompleting
import care.intouch.app.core.navigation.TaskEstimate
import care.intouch.app.core.navigation.TaskIntroduction
import care.intouch.app.feature.plan.presentation.ui.TaskCompletingScreen
import care.intouch.app.feature.plan.presentation.ui.TaskEstimateScreen
import care.intouch.app.feature.plan.presentation.ui.TaskIntroductionScreen
import care.intouch.app.feature.plan.presentation.ui.TaskScreen

fun NavGraphBuilder.addNestedPlanGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Task.route,
        route = PlanRouteBranch.route,
    ) {

        composable(route = Task.route) {
            TaskScreen(
                goToTaskIntroductionScreen = {
                    navController.navigate(route = TaskIntroduction.route)
                }
            )
        }

        composable(route = TaskIntroduction.route) {
            TaskIntroductionScreen(
                goToTaskCompletingScreen = {
                    navController.navigate(route = TaskCompleting.route)
                }
            )
        }

        composable(route = TaskEstimate.route) {
            TaskEstimateScreen()
        }

        composable(route = TaskCompleting.route) {
            TaskCompletingScreen(
                goToTaskEstimateScreen = {
                    navController.navigate(route = TaskEstimate.route)
                }
            )
        }
    }
}