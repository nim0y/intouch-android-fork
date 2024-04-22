package care.intouch.app.feature.common.domain.models

sealed class ErrorEntity(open val message: String) {
    data class UnknownError(override val message: String = NO_ERROR_MESSAGE) : ErrorEntity(message)
    sealed class NetworksError(override val message: String) : ErrorEntity(message) {
        data class NoInternetConnection(override val message: String) : NetworksError(message)
        data class NoConnectionToServer(override val message: String) : NetworksError(message)
    }

    sealed class Authentication(message: String) : ErrorEntity(message) {
        data class Unknown(
            override val message: String = NO_ERROR_MESSAGE
        ) : Authentication(message)

        data class InvalidToken(override val message: String) : Authentication(message)
        data class UserNotExist(override val message: String) : Authentication(message)
        data class AlreadyActivated(override val message: String) : Authentication(message)
    }

    companion object {
        private const val NO_ERROR_MESSAGE = "No error message"
    }
}

