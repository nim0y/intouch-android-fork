package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.response.GetUserResponse
import retrofit2.http.GET

interface UserApiService {
    @GET("/api/v1/get-user")
    suspend fun getUser(): GetUserResponse
}