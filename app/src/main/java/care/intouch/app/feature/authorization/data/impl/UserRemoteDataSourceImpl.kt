package care.intouch.app.feature.authorization.data.impl

import care.intouch.app.feature.authorization.data.NetworkToUserExceptionMapper
import care.intouch.app.feature.authorization.data.UserDataMapper
import care.intouch.app.feature.authorization.data.api.UserApiService
import care.intouch.app.feature.authorization.data.api.UserRemoteDataSource
import care.intouch.app.feature.authorization.data.models.UserDto
import care.intouch.app.feature.common.data.models.exception.NetworkException
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userApiService: UserApiService,
    private val dataMapper: UserDataMapper,
    private val networkToUserExceptionMapper: NetworkToUserExceptionMapper
) : UserRemoteDataSource {
    override suspend fun getUser(): UserDto {
        return try {
            val response = userApiService.getUser()
            dataMapper.toUserDto(response)
        } catch (exception: NetworkException) {
            throw networkToUserExceptionMapper.handleException(exception)
        }
    }
}