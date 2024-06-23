package care.intouch.app.feature.authorization.data.impl

import android.content.SharedPreferences
import androidx.core.content.edit
import care.intouch.app.feature.authorization.data.api.UserRemoteDataSource
import care.intouch.app.feature.authorization.data.models.mappers.UserExceptionToErrorMapper
import care.intouch.app.feature.authorization.data.models.mappers.UserToDomainMapper
import care.intouch.app.feature.authorization.data.models.response.UserResponse
import care.intouch.app.feature.authorization.domain.api.UserRepository
import care.intouch.app.feature.authorization.domain.models.User
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val userToDomainMapper: UserToDomainMapper,
    private val exceptionToErrorMapper: UserExceptionToErrorMapper,
    private val sharedPreferences: SharedPreferences,
    private val json: Json
) : UserRepository {
    override suspend fun getUser(): Resource<User, ErrorEntity> {
        try {
            val response = remoteDataSource.getUser()
            saveProfileDataToSharedPreferences(response)
            return Resource.Success(userToDomainMapper.toUser(response))

        } catch (exception: Exception) {
            val error = exceptionToErrorMapper.handleException(exception)
            return Resource.Error(error)
        }
    }

    private fun saveProfileDataToSharedPreferences(userResponse: UserResponse){
        val user: User = userToDomainMapper.toUser(userResponse)
        val data = json.encodeToString(user)
        sharedPreferences.edit{ putString(USER_DATA, data)}
    }

    companion object {
        const val USER_DATA = "USER_DATA"
    }
}