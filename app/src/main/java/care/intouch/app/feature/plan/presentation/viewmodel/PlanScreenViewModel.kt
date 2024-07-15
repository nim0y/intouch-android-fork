package care.intouch.app.feature.plan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import care.intouch.app.feature.plan.domain.models.AssignmentStatus
import care.intouch.app.feature.plan.domain.useCase.GetAssignmentsUseCase
import care.intouch.app.feature.plan.presentation.models.PlanScreenEvent
import care.intouch.app.feature.plan.presentation.models.PlanScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PlanScreenViewModel @Inject constructor(
    private val getAssignmentsUseCase: GetAssignmentsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlanScreenState())
    val uiState: StateFlow<PlanScreenState> = _uiState.asStateFlow()

    init {
        getAssignments()
    }

    fun onEvent(planScreenEvent: PlanScreenEvent) {
        when(planScreenEvent) {

            PlanScreenEvent.GetAssignmentsEvent -> {
                getAssignments()
            }

            is PlanScreenEvent.SetDialogueVisibilityEvent -> {
                setDialogueIsVisible(planScreenEvent.isVisible)
            }

            is PlanScreenEvent.FilterAssignmentsEvent -> {
                updateFilterAssignments(planScreenEvent.assignmentStatus)
            }
        }
    }

    private fun getAssignments() {
        val assignments = getAssignmentsUseCase()

        _uiState.update { planScreenState ->
            planScreenState.copy(
                assignments = assignments,
                filteredAssignments = assignments
            )
        }
    }

    private fun updateFilterAssignments(
        filterStatus: AssignmentStatus
    ) {
        if (filterStatus == AssignmentStatus.SHOW_ALL) {
            _uiState.update { planScreenState ->
                planScreenState.copy(
                    filteredAssignments = _uiState.value.assignments
                )
            }
        } else {
            val filteredAssignments = _uiState.value.assignments
                .filter { assignment -> assignment.status == filterStatus  }

            _uiState.update { planScreenState ->
                planScreenState.copy(
                    filteredAssignments = filteredAssignments
                )
            }
        }
    }

    private fun setDialogueIsVisible(isVisible: Boolean) {
        _uiState.update { planScreenState ->
            planScreenState.copy(
                isDialogueVisible = isVisible
            )
        }
    }
}