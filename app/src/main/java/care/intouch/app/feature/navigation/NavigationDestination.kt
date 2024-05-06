package care.intouch.app.feature.navigation

sealed class NavigationDestination(val destination: String) {
    data object Registration: NavigationDestination("registrationScreen")
}