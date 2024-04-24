package care.intouch.app.feature.authorization.domain.models.mappers

import care.intouch.app.feature.authorization.data.models.UserDto
import care.intouch.app.feature.authorization.domain.models.User
import javax.inject.Inject

class UserDomainMapper @Inject constructor() {
    fun toUser(userDto: UserDto): User {
        return User(
            id = userDto.id ?: DEFAULT_ID,
            firstName = userDto.firstName ?: DEFAULT_FIRST_NAME,
            lastName = userDto.lastName ?: DEFAULT_LAST_NAME,
            email = userDto.email ?: DEFAULT_EMAIL,
            acceptPolicy = userDto.acceptPolicy ?: DEFAULT_ACCEPT_POLICY,
            newEmailTemp = userDto.newEmailTemp ?: DEFAULT_NEW_EMAIL_TEMP,
            newEmailChanging = userDto.newEmailChanging ?: DEFAULT_NEW_EMAIL_CHANGING
        )
    }

    companion object {
        private const val DEFAULT_ID = -1
        private const val DEFAULT_FIRST_NAME = ""
        private const val DEFAULT_LAST_NAME = ""
        private const val DEFAULT_EMAIL = ""
        private const val DEFAULT_ACCEPT_POLICY = false
        private const val DEFAULT_NEW_EMAIL_TEMP = ""
        private const val DEFAULT_NEW_EMAIL_CHANGING = false
    }

}