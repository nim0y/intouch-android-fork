package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.NetworkToAuthenticationExceptionMapper
import care.intouch.app.feature.authorization.data.models.AuthenticationDataMapper
import care.intouch.app.feature.authorization.data.models.ConfirmEmailDto
import care.intouch.app.feature.common.data.utill.exception.NetworkException
import javax.inject.Inject

class AuthenticationRemoteDataSourceImpl @Inject constructor(
    private val apiService: AuthenticationApiService,
    private val dataMapper: AuthenticationDataMapper,
    private val networkToAuthenticationExceptionMapper: NetworkToAuthenticationExceptionMapper
) :
    AuthenticationRemoteDataSource {
    override suspend fun confirmEmail(id: String, token: String): ConfirmEmailDto {
        return try {
            val response = apiService.confirmEmail(id, token)
            dataMapper.toConfirmEmailDto(response)
        } catch (exception: NetworkException) {
            throw networkToAuthenticationExceptionMapper.handleException(exception)
        }
    }
}