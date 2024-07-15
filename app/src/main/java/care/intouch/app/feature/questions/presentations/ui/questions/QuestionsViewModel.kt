package care.intouch.app.feature.questions.presentations.ui.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.questions.domain.models.Assignments
import care.intouch.app.feature.questions.domain.useCase.GetAssignmentsUseCase
import care.intouch.app.feature.questions.presentations.ui.models.QuestionsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val getAssignments: GetAssignmentsUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(QuestionsState(""))
    val state = _state.asStateFlow()
    private lateinit var ttt: Assignments

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAssignments.invoke(559).onSuccess {
                ttt = it
            }
        }

    }

    fun onEvent() {
        TODO()
    }
}