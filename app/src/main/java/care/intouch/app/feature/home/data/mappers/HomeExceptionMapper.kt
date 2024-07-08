package care.intouch.app.feature.home.data.mappers

import care.intouch.app.feature.authorization.data.models.exception.UserException
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.common.domain.models.BaseExceptionToErrorMapper
import kotlin.Exception

class HomeExceptionMapper : BaseExceptionToErrorMapper() {
    override fun handleSpecificException(exception: Exception): ErrorEntity {
        return when (exception) {
            is UserException -> handleHomeException(exception)
            else -> handleHomeUnknownException(exception)
        }
    }

    private fun handleHomeException(exception: Exception): ErrorEntity {

    }

    private fun handleHomeUnknownException(exception: Exception): ErrorEntity {

    }
}