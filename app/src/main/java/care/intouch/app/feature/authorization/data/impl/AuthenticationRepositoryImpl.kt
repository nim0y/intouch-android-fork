package care.intouch.app.feature.authorization.data.impl

import care.intouch.app.feature.authorization.data.api.AuthenticationRemoteDataSource
import care.intouch.app.feature.authorization.domain.api.AuthenticationRepository
import care.intouch.app.feature.authorization.domain.models.AuthenticationDomainMapper
import care.intouch.app.feature.authorization.domain.models.AuthenticationOutputData
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.models.BaseExceptionToErrorMapper
import care.intouch.app.feature.common.domain.models.ErrorEntity
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthenticationRemoteDataSource,
    private val domainMapper: AuthenticationDomainMapper,
    private val exceptionToErrorMapper: BaseExceptionToErrorMapper
) : AuthenticationRepository {
    override suspend fun confirmEmail(
        id: String,
        code: String
    ): Resource<AuthenticationOutputData, ErrorEntity> {
        return try {
            val response = remoteDataSource.confirmEmail(id, code)
            Resource.Success(domainMapper.toAuthenticationOutputData(response))

        } catch (exception: Exception) {
            val error = exceptionToErrorMapper.handleException(exception)
            return Resource.Error(error)
        }
    }
}