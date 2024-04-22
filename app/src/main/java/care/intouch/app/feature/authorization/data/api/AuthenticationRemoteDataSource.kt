package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.AuthenticationOutputDto

interface AuthenticationRemoteDataSource {
    suspend fun confirmEmail(id: String, token: String): AuthenticationOutputDto
}