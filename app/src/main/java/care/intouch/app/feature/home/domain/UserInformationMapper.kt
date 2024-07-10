package care.intouch.app.feature.home.domain

import care.intouch.app.feature.authorization.domain.models.User
import care.intouch.app.feature.home.presentation.models.UserInformation

class UserInformationMapper {
    fun map(user: User?): UserInformation {
        return UserInformation(
            userId = user?.id ?: 0,
            userName = user?.firstName ?: "Bob"
        )
    }
}