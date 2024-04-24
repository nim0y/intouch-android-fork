package care.intouch.app.feature.authorization.data.impl

import care.intouch.app.feature.authorization.data.api.AuthenticationApiService
import care.intouch.app.feature.authorization.data.api.AuthenticationRemoteDataSource
import care.intouch.app.feature.authorization.data.di.AuthApiServiceWithoutAuth
import care.intouch.app.feature.authorization.data.models.AuthenticationOutputDto
import care.intouch.app.feature.authorization.data.models.mappers.AuthenticationDataMapper
import care.intouch.app.feature.authorization.data.models.mappers.NetworkToAuthenticationExceptionMapper
import care.intouch.app.feature.common.data.models.exception.NetworkException
import javax.inject.Inject

class AuthenticationRemoteDataSourceImpl @Inject constructor(
    @AuthApiServiceWithoutAuth private val apiService: AuthenticationApiService,
    private val dataMapper: AuthenticationDataMapper,
    private val networkToAuthenticationExceptionMapper: NetworkToAuthenticationExceptionMapper
) :
    AuthenticationRemoteDataSource {
    override suspend fun confirmEmail(id: Int, token: String): AuthenticationOutputDto {
        return try {
            val response = apiService.confirmEmail(id, token)
            dataMapper.toConfirmEmailDto(response)
        } catch (exception: NetworkException) {
            throw networkToAuthenticationExceptionMapper.handleException(exception)
        }
    }
}