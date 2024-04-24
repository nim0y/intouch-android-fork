package care.intouch.app.feature.authorization.data.models.mappers

import care.intouch.app.feature.authorization.data.models.UserDto
import care.intouch.app.feature.authorization.data.models.response.GetUserResponse
import javax.inject.Inject

class UserDataMapper @Inject constructor() {
    fun toUserDto(users: List<GetUserResponse>): UserDto {
        val user = users.first()
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