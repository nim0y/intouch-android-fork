package care.intouch.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import care.intouch.app.feature.diary.presentation.ui.DiaryNoteScreen
import care.intouch.app.feature.home.presentation.ui.HomeScreen
import care.intouch.app.feature.plan.presentation.ui.PlanScreen
import care.intouch.app.feature.profile.presentation.ui.ProfileScreen

@Composable
fun BottomNavGraph(
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
                    navController.navigate(route = Route.TASK_ROUTE)
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

        composable(route = Route.TASK_ROUTE) {
            TaskNavHost()
        }
    }
}


