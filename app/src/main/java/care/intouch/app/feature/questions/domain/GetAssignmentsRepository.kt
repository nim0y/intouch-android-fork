package care.intouch.app.feature.questions.domain

import care.intouch.app.feature.questions.domain.models.Assignments

interface GetAssignmentsRepository {
    suspend fun getAssignments(id: Int): Result<Assignments>
}


