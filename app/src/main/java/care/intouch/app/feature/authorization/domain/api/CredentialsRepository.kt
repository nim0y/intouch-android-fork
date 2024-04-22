package care.intouch.app.feature.authorization.domain.api

interface CredentialsRepository {
    suspend fun saveCredentials(accessToken: String, refreshToken: String)
}