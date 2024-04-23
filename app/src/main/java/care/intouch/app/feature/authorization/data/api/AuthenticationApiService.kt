package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.TokensRequest
import care.intouch.app.feature.authorization.data.models.response.ConfirmEmailResponse
import care.intouch.app.feature.authorization.data.models.response.TokensResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthenticationApiService {
    @GET("/api/v1/confirm-email/{id}/{token}")
    suspend fun confirmEmail(
        @Path("id") id: Int,
        @Path("token") token: String
    ): ConfirmEmailResponse

    @POST("/api/v1/confirm-email/{id}/{token}")
    fun getTokensByRefreshToken(
        @Body tokensRequest: TokensRequest
    ): Call<TokensResponse>
}