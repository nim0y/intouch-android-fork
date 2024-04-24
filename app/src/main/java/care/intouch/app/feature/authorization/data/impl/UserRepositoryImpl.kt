package care.intouch.app.feature.authorization.data.impl

import care.intouch.app.feature.authorization.data.api.UserRemoteDataSource
import care.intouch.app.feature.authorization.data.di.UserExceptionMapper
import care.intouch.app.feature.authorization.domain.api.UserRepository
import care.intouch.app.feature.authorization.domain.models.User
import care.intouch.app.feature.authorization.domain.models.mappers.UserDomainMapper
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.common.domain.models.BaseExceptionToErrorMapper

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val userDomainMapper: UserDomainMapper,
    @UserExceptionMapper private val exceptionToErrorMapper: BaseExceptionToErrorMapper

) : UserRepository {
    override suspend fun getUserData(): Resource<User, ErrorEntity> {
        try {
            val response = remoteDataSource.getUser()
            return Resource.Success(userDomainMapper.toUser(response))

        } catch (exception: Exception) {
            val error = exceptionToErrorMapper.handleException(exception)
            return Resource.Error(error)
        }
    }
}