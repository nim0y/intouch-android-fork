package care.intouch.app.feature.common.domain.models

import app.cashadvisor.common.utill.extensions.logNetworkError
import java.net.ConnectException

abstract class BaseExceptionToErrorMapper {

    abstract fun handleSpecificException(exception: Exception): ErrorEntity

    fun handleException(exception: Exception): ErrorEntity {
        return when (exception) {
            is ConnectException -> {
                logNetworkError(exception.message)
                handleNetworkError(exception)
            }

            else -> {
                logNetworkError(exception.message)
                handleSpecificException(exception)
            }
        }
    }

    private fun handleNetworkError(exception: Exception): ErrorEntity {
        return ErrorEntity.NetworksError.NoConnectionToServer(
            exception.message ?: NO_CONNECTION_TO_SERVER
        )
    }

    companion object {
        const val NO_CONNECTION_TO_SERVER = "No connection to server"
    }
}

