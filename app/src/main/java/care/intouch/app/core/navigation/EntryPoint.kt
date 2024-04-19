package care.intouch.app.core.navigation

sealed class EntryPoint(val stringValue: String, val boolValue: Boolean) {
    data class Authentication(val value: String = "Authentication", val boolState: Boolean = true)
        : EntryPoint(stringValue = value, boolValue = boolState)

    data class Registration(val value: String = "Registration", val boolState: Boolean = false) :
        EntryPoint(stringValue = value, boolValue = boolState)
}