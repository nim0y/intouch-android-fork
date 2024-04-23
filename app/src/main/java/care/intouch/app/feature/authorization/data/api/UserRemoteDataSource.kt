package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.UserDto

interface UserRemoteDataSource {
    suspend fun getUser(): UserDto
}