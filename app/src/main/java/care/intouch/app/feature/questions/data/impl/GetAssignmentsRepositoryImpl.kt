package care.intouch.app.feature.questions.data.impl

import care.intouch.app.feature.questions.data.api.GetAssignmentsApi
import care.intouch.app.feature.questions.domain.models.Assignments
import care.intouch.app.feature.questions.domain.useCase.GetAssignmentsRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

class GetAssignmentsRepositoryImpl  @Inject constructor(
    private val getAssignmentsApi: GetAssignmentsApi,
    private val json: Json
): GetAssignmentsRepository {
    override suspend fun getAssignments(id: Int): Result<Assignments> {
        try {
            val response = getAssignmentsApi.getAssignments(id)
            return Result.success()
        }

    }
}