package care.intouch.app.core.navigation.navhost

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import care.intouch.app.core.navigation.CreatingNoteIntroduction
import care.intouch.app.core.navigation.DiaryEntries
import care.intouch.app.core.navigation.DiaryRouteBranch
import care.intouch.app.core.navigation.EmotionChoice
import care.intouch.app.feature.diary.presentation.ui.CreatingNoteIntroductionScreen
import care.intouch.app.feature.diary.presentation.ui.DiaryEntriesScreen
import care.intouch.app.feature.diary.presentation.ui.EmotionChoiceScreen

fun NavGraphBuilder.addNestedDiaryGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = CreatingNoteIntroduction.route,
        route = DiaryRouteBranch.route
    ) {
        composable(route = CreatingNoteIntroduction.route) {
            CreatingNoteIntroductionScreen(
                goToDiaryEntriesScreen = {
                    navController.navigate(route = DiaryEntries.route)
                }
            )
        }

        composable(route = DiaryEntries.route) {
            DiaryEntriesScreen(
                goToEmotionChoiceScreen = {
                    navController.navigate(route = EmotionChoice.route)
                }
            )
        }

        composable(route = EmotionChoice.route) {
            EmotionChoiceScreen(
                goToDiaryEntriesScreen = {
                    navController.navigate(route = DiaryEntries.route)
                }
            )
        }
    }
}