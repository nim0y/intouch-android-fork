package care.intouch.app.feature.authorization.data

import care.intouch.app.feature.authorization.data.models.UserDto
import care.intouch.app.feature.authorization.data.models.response.GetUserResponse
import javax.inject.Inject

class UserDataMapper @Inject constructor() {
    fun toUserDto(user: GetUserResponse): UserDto {
        return UserDto(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            acceptPolicy = user.acceptPolicy,
            newEmailChanging = user.newEmailChanging,
            newEmailTemp = user.newEmailTemp,
        )
    }
}