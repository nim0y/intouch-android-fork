package care.intouch.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import care.intouch.app.feature.plan.presentation.ui.TaskCompletingScreen
import care.intouch.app.feature.plan.presentation.ui.TaskEstimateScreen
import care.intouch.app.feature.plan.presentation.ui.TaskIntroductionScreen
import care.intouch.app.feature.plan.presentation.ui.TaskScreen

@Composable
fun TaskNavHost(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = Route.TASK
    ) {
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