package care.intouch.app.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import care.intouch.uikit.ui.navigation.CustomBottomNavBar


@Composable
fun BottomNav() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Modifier.padding(it)
        RootNavGraph(
            navController = navController,
            startDestination = Route.HOME
        )
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {

    CustomBottomNavBar(
        navController = navController,
        screenRoute1 = Route.HOME,
        screenRoute2 = Route.PLAN,
        screenRoute3 = "", // TODO This screenRoure for Plus Button
        screenRoute4 = Route.DIARY,
        screenRoute5 = Route.PROFILE
    )
}