package care.intouch.app.feature.plan.presentation.viewmodel

import care.intouch.app.feature.plan.domain.models.Assignment
import care.intouch.app.feature.plan.domain.useCase.GetAssignmentsUseCase

class PlanScreenViewModel(
    private val getAssignmentsUseCase: GetAssignmentsUseCase.Base
    = GetAssignmentsUseCase.Base()
) {

    fun getAssignments(): List<Assignment> {
        return getAssignmentsUseCase()
    }
}