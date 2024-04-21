package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.response.ConfirmEmailResponse
import care.intouch.app.feature.authorization.data.models.response.GetUserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthenticationApi {
    @GET("/api/v1/confirm_email/{id}/{token}")
    fun confirmEmail(@Path("id") id: String, @Path("token") token: String): ConfirmEmailResponse

    @GET("/api/v1/get_user/")
    fun getUser(): GetUserResponse
}