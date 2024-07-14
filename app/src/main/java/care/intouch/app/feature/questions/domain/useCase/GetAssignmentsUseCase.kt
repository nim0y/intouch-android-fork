package care.intouch.app.feature.questions.domain.useCase

import care.intouch.app.feature.questions.domain.models.Assignments
import javax.inject.Inject

interface GetAssignmentsUseCase {
    suspend operator fun invoke(id: Int): Result<Assignments>

    class Base @Inject constructor(
        private val getAssignmentsRepository: GetAssignmentsRepository
    ): GetAssignmentsUseCase {
        override suspend fun invoke(id: Int): Result<Assignments> {
            return getAssignmentsRepository.getAssignments(id)
        }
    }
}