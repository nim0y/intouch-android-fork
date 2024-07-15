package care.intouch.app.feature.questions.data.impl

import care.intouch.app.feature.authorization.data.models.exception.AuthenticationException
import care.intouch.app.feature.authorization.data.models.mappers.NetworkToUserExceptionMapper.Companion.COULD_NOT_CONVERT_TO_ERROR_RESPONSE
import care.intouch.app.feature.common.data.models.exception.NetworkException
import care.intouch.app.feature.questions.data.api.GetAssignmentsApi
import care.intouch.app.feature.questions.data.converters.AssignmentsConvertor
import care.intouch.app.feature.questions.domain.models.Assignments
import care.intouch.app.feature.questions.domain.useCase.GetAssignmentsRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

class GetAssignmentsRepositoryImpl  @Inject constructor(
    private val getAssignmentsApi: GetAssignmentsApi,
    private val json: Json,
    private val convertor: AssignmentsConvertor
): GetAssignmentsRepository {
    override suspend fun getAssignments(id: Int): Result<Assignments> {
        try {
            val response = getAssignmentsApi.getAssignments(id)
            return Result.success(
                convertor.map(response)
            )
        } catch (e: NetworkException) {
            return when(e) {
                is NetworkException.BadRequest -> {
                    val response = handleErrorResponse<Assignments>(e.errorBody)
                    Result.failure(
                        NetworkException.BadRequest(
                            "Надо сюда поймать ошибку какую-нибудь",
                            e.httpStatusCode
                        )
                    )
                }
                else -> {
                    Result.failure(e)
                }
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
}