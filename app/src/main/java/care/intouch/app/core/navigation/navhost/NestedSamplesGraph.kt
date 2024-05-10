package care.intouch.app.core.navigation.navhost

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import care.intouch.app.core.navigation.ButtonsSample
import care.intouch.app.core.navigation.ChipsSample
import care.intouch.app.core.navigation.MultilineTextFieldSample
import care.intouch.app.core.navigation.NavigationSample
import care.intouch.app.core.navigation.OneLineTextFieldSample
import care.intouch.app.core.navigation.PasswordTextFieldSample
import care.intouch.app.core.navigation.SampleRouteBranch
import care.intouch.app.core.navigation.SliderSample
import care.intouch.app.core.navigation.ToggleSample
import care.intouch.app.ui.uiKitSamples.samples.ButtonSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.MultilineTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.NavigationAppSample
import care.intouch.app.ui.uiKitSamples.samples.OneLineTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.PasswordTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.RegularChipsSample
import care.intouch.app.ui.uiKitSamples.samples.SliderSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.ToggleSampleScreen

fun NavGraphBuilder.addNestedSamplesGraph() {
    navigation(
        startDestination = ButtonsSample.route,
        route = SampleRouteBranch.route
    ) {

        composable(route = ButtonsSample.route) {
            ButtonSampleScreen()
        }

        composable(route = ChipsSample.route) {
            RegularChipsSample()
        }

        composable(route = MultilineTextFieldSample.route) {
            MultilineTextFieldSampleScreen()
        }

        composable(route = NavigationSample.route) {
            NavigationAppSample()
        }

        composable(route = OneLineTextFieldSample.route) {
            OneLineTextFieldSampleScreen()
        }

        composable(route = PasswordTextFieldSample.route) {
            PasswordTextFieldSampleScreen()
        }

        composable(route = SliderSample.route) {
            SliderSampleScreen()
        }

        composable(route = ToggleSample.route) {
            ToggleSampleScreen()
        }
    }
}