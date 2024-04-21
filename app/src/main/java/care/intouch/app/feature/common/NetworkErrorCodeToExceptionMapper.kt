package care.intouch.app.feature.common


import care.intouch.app.feature.common.data.utill.exception.NetworkException
import java.io.IOException
import javax.inject.Inject

class NetworkErrorCodeToExceptionMapper @Inject constructor() {

    fun getException(errorMessage: String, responseCode: Int): IOException {
        return when (responseCode) {
            400 -> NetworkException.BadRequest(errorMessage, responseCode)
            404 -> NetworkException.NotFound(errorMessage, responseCode)
            500 -> NetworkException.InternalServerError(errorMessage, responseCode)

            else -> NetworkException.Undefined(errorMessage, responseCode)
        }
    }

}