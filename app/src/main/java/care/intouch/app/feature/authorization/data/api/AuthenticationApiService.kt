package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.response.ConfirmEmailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthenticationApiService {
    @GET("/api/v1/confirm_email/{id}/{token}")
    suspend fun confirmEmail(
        @Path("id") id: String,
        @Path("token") token: String
    ): ConfirmEmailResponse
}