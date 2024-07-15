package care.intouch.app.feature.questions.presentations.ui.introductory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.questions.domain.useCase.GetAssignmentsUseCase
import care.intouch.app.feature.questions.presentations.ui.models.IntroductoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroductoryViewModel @Inject constructor(
    private val getAssignments: GetAssignmentsUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(IntroductoryState("", "",""))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAssignments.invoke(1).onSuccess { assignment ->
                _state.update {
                    it.copy(
                        assignmentTitle = assignment.title,
                        assignmentDescription = assignment.text,
                        assignmentImageUrl = assignment.imageUrl
                    )
                }

            }
        }
    }


    fun onEvent() {
        TODO()
    }
}