package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.ConfirmEmailDto

interface AuthenticationRemoteDataSource {
    suspend fun confirmEmail(id: String, token: String): ConfirmEmailDto
}