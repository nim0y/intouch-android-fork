package care.intouch.app.feature.questions.data.impl

import care.intouch.app.feature.questions.data.api.AssignmentsApi
import kotlinx.serialization.json.Json
import javax.inject.Inject

class GetAssignmentsRepositoryImpl  @Inject constructor(
    private val assignmentsApi: AssignmentsApi,
    private val json: Json
) {
}