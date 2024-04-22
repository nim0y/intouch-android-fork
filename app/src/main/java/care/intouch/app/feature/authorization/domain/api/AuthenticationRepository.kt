package care.intouch.app.feature.authorization.domain.api

import care.intouch.app.feature.authorization.domain.models.AuthenticationOutputData
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.models.ErrorEntity

interface AuthenticationRepository {
    suspend fun confirmEmail(
        id: String,
        code: String
    ): Resource<AuthenticationOutputData, ErrorEntity>
}