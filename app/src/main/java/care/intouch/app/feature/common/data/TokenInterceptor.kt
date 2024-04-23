package care.intouch.app.feature.common.data

import care.intouch.app.feature.authorization.data.api.AuthenticationApiService
import care.intouch.app.feature.authorization.data.di.AuthApiServiceWithoutToken
import care.intouch.app.feature.authorization.data.models.TokensRequest
import care.intouch.app.feature.authorization.data.models.response.TokensResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    @AuthApiServiceWithoutToken private val authenticationApiService: AuthenticationApiService
) : Interceptor {

    private val accountLocalDataSource = AccountLocalDataSource()
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = createRequestOnChainWithToken(chain, getAccessToken())
        val response = chain.proceed(request)
        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            synchronized(this) {
                val oldAccessToken =
                    response.request.header(HEADER_NAME)?.replace(BEARER, EMPTY_STRING)
                val currentAccessToken = getAccessToken()
                if (oldAccessToken != currentAccessToken) {
                    return chain.proceed(
                        createRequestOnResponseWithToken(
                            response,
                            currentAccessToken
                        )
                    )
                } else {
                    val responseNewTokens = getNewTokens(getRefreshToken())
                    if (responseNewTokens.code() == HttpURLConnection.HTTP_OK && responseNewTokens.body() != null) {
                        responseNewTokens.body()?.let { newTokens ->
                            accountLocalDataSource.saveAccountInformation(
                                AccountModel(
                                    accessToken = newTokens.access,
                                    refreshToken = newTokens.refresh
                                )
                            )
                        }
                        return chain.proceed(
                            createRequestOnResponseWithToken(
                                response,
                                getAccessToken()
                            )
                        )
                    } else {
                        accountLocalDataSource.clearAccountInformation()
                    }
                }
            }
        }
        return response
    }

    private fun createRequestOnChainWithToken(
        chain: Interceptor.Chain,
        accessToken: String
    ): Request {
        return chain.request().newBuilder().run {
            if (accessToken.isNotBlank()) {
                header(HEADER_NAME, BEARER + accessToken)
            }
            build()
        }
    }

    private fun createRequestOnResponseWithToken(response: Response, accessToken: String): Request {
        return response.request.newBuilder()
            .header(HEADER_NAME, "$BEARER${accessToken}")
            .build()
    }


    private fun getNewTokens(refreshTokens: String): retrofit2.Response<TokensResponse> =
        runBlocking(Dispatchers.IO) {
            return@runBlocking authenticationApiService.getTokensByRefreshToken(
                TokensRequest(refresh = refreshTokens)
            ).execute()
        }

    private fun getAccessToken(): String {
        return accountLocalDataSource.getAccountInformation()?.accessToken ?: EMPTY_STRING
    }

    private fun getRefreshToken(): String {
        return accountLocalDataSource.getAccountInformation()?.refreshToken ?: EMPTY_STRING
    }

    companion object {
        const val HEADER_NAME = "Authorization"
        const val BEARER = "Bearer "
        const val EMPTY_STRING = ""
    }
}