package care.intouch.app.core.navigation

interface IntouchDestination {
    val route: String
}

// Authorization
object AuthorizationRouteBranch: IntouchDestination {
    override val route = "AUTHORIZATION_ROUTE_BRANCH"
}
object Registration: IntouchDestination {
    override val route = "REGISTRATION"
}
object Authentication: IntouchDestination {
    override val route = "AUTHENTICATION"
}
object PasswordRecovery: IntouchDestination {
    override val route = "PASSWORD_RECOVERY"
}
object PinCodeConfirmation: IntouchDestination {
    override val route = "PIN_CODE_CONFIRMATION"
}
object PinCodeInstallation: IntouchDestination {
    override val route = "PIN_CODE_INSTALLATION"
}
object PinCodeEnter: IntouchDestination {
    override val route = "PIN_CODE_ENTER"
}
object SendingNotification: IntouchDestination {
    override val route = "SENDING_NOTIFICATION"
}

//Bottom Navigation
object MainNav: IntouchDestination {
    override val route = "MAIN_NAV"
}
object Home: IntouchDestination {
    override val route = "HOME"
}
object Plan: IntouchDestination {
    override val route = "PLAN"
}
object Diary: IntouchDestination {
    override val route = "DIARY"
}
object Profile: IntouchDestination {
    override val route = "PROFILE"
}

// Plan branch
object PlanRouteBranch: IntouchDestination {
    override val route = "PLAN_ROUTE_BRANCH"
}
object Task: IntouchDestination {
    override val route = "TASK"
}
object TaskIntroduction: IntouchDestination {
    override val route = "TASK_INTRODUCTION"
}
object TaskEstimate: IntouchDestination {
    override val route = "TASK_ESTIMATE"
}
object TaskCompleting: IntouchDestination {
    override val route = "TASK_COMPLETING"
}

// Diary branch
object DiaryRouteBranch: IntouchDestination {
    override val route = "DIARY_ROUTE_BRANCH"
}
object CreatingNoteIntroduction: IntouchDestination {
    override val route = "CREATING_NOTE_INTRODUCTION"
}
object DiaryEntries: IntouchDestination {
    override val route = "DIARY_ENTRIES"
}
object EmotionChoice: IntouchDestination {
    override val route = "EMOTIONS_CHOICE"
}

// Profile branch
object ProfileRouteBranch: IntouchDestination {
    override val route = "PROFILE_ROUTE_BRANCH"
}
object PasswordChange: IntouchDestination {
    override val route = "PASSWORD_CHANGE"
}
object NewPinCode: IntouchDestination {
    override val route = "NEW_PIN_CODE"
}
object PinCodeChange: IntouchDestination {
    override val route = "PIN_CODE_CHANGE"
}
object PinCodeConfirm: IntouchDestination {
    override val route = "PIN_CODE_CONFIRM"
}
object SuccessfulPinCodeChange: IntouchDestination {
    override val route = "SUCCESSFUL_PIN_CODE_CHANGE"
}