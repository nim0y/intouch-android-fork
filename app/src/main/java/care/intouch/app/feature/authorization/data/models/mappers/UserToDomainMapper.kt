package care.intouch.app.feature.authorization.data.models.mappers

import care.intouch.app.feature.authorization.data.models.response.UserResponse
import care.intouch.app.feature.authorization.domain.models.User
import javax.inject.Inject

class UserToDomainMapper @Inject constructor() {
    fun toUser(response: UserResponse): User {
        return User(
            id = response.id ?: DEFAULT_ID,
            firstName = response.firstName ?: DEFAULT_FIRST_NAME,
            lastName = response.lastName ?: DEFAULT_LAST_NAME,
            email = response.email ?: DEFAULT_EMAIL,
            acceptPolicy = response.acceptPolicy ?: DEFAULT_ACCEPT_POLICY,
            newEmailTemp = response.newEmailTemp ?: DEFAULT_NEW_EMAIL_TEMP,
            newEmailChanging = response.newEmailChanging ?: DEFAULT_NEW_EMAIL_CHANGING
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