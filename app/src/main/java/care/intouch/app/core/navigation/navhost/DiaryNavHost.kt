package care.intouch.app.core.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import care.intouch.app.core.navigation.Route
import care.intouch.app.feature.diary.presentation.ui.CreatingNoteIntroductionScreen
import care.intouch.app.feature.diary.presentation.ui.DiaryEntriesScreen
import care.intouch.app.feature.diary.presentation.ui.EmotionChoiceScreen

@Composable
fun DiaryNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(route = Route.CREATING_NOTE_INTRODUCTION) {
            CreatingNoteIntroductionScreen(
                goToDiaryEntriesScreen = {
                    navController.navigate(route = Route.DIARY_ENTRIES)
                }
            )
        }

        composable(route = Route.DIARY_ENTRIES) {
            DiaryEntriesScreen(
                goToEmotionChoiceScreen = {
                    navController.navigate(route = Route.EMOTIONS_CHOICE)
                }
            )
        }

        composable(route = Route.EMOTIONS_CHOICE) {
            EmotionChoiceScreen(
                goToDiaryEntriesScreen = {
                    navController.navigate(route = Route.DIARY_ENTRIES)
                }
            )
        }
    }
}