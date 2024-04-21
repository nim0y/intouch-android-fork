package care.intouch.app.feature.authorization.data

import care.intouch.app.feature.authorization.data.models.response.ErrorResponse
import care.intouch.app.feature.common.data.utill.exception.AuthenticationException
import care.intouch.app.feature.common.data.utill.exception.NetworkException
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NetworkToAuthenticationExceptionMapper @Inject constructor(private val json: Json) {
    fun handleException(exception: NetworkException): AuthenticationException {
        return when (exception) {
            is NetworkException.Unauthorized -> {
                val response = handleErrorResponse<ErrorResponse>(exception.errorBody)
                AuthenticationException.Authentication.InvalidToken(
                    message = response.error ?: BLANC_ERROR_MESSAGE
                )
            }

            is NetworkException.NotFound -> {
                val response = handleErrorResponse<ErrorResponse>(exception.errorBody)
                AuthenticationException.Authentication.NotFound(
                    message = response.detail ?: BLANC_ERROR_MESSAGE,
                )
            }

            else -> {
                handleCommonException(exception)
            }
        }
    }

    private fun handleCommonException(exception: NetworkException): AuthenticationException {
        return when (exception) {
            is NetworkException.NoInternetConnection -> {
                AuthenticationException.NoConnection(message = exception.errorBody)
            }

            else -> {
                AuthenticationException.Undefined(message = exception.errorBody)
            }
        }
    }

    private inline fun <reified T> handleErrorResponse(errorMessage: String): T {
        try {
            return json.decodeFromString<T>(errorMessage)

        } catch (e: Exception) {
            throw AuthenticationException.Undefined(COULD_NOT_CONVERT_TO_ERROR_RESPONSE)
        }
    }

    companion object {
        const val COULD_NOT_CONVERT_TO_ERROR_RESPONSE = "Couldn't convert to ErrorResponse"
        const val BLANC_ERROR_MESSAGE = "No error message"
    }
}