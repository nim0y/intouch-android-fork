package care.intouch.app.feature.questions.presentations.ui.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.questions.domain.useCase.GetAssignmentsUseCase
import care.intouch.app.feature.questions.presentations.ui.models.QuestionsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val getAssignments: GetAssignmentsUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(
        QuestionsState(
            0,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            0,
            "",
            emptyList(),
            0,
            "",
            0,
            false,
            null,
            "",
            0
            ))
    val state = _state.asStateFlow()
    //private lateinit var ttt: Assignments

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAssignments.invoke(1).onSuccess { assignment ->
                _state.update {
                    it.copy(
                        id = assignment.id,
                        title = assignment.title,
                        text = assignment.text,
                        updateDate = assignment.updateDate,
                        addDate = assignment.addDate,
                        assignmentType = assignment.assignmentType,
                        status = assignment.status,
                        tags = assignment.tags,
                        language = assignment.language,
                        share = assignment.share,
                        imageUrl = assignment.imageUrl,
                        blocks = assignment.blocks,
                        author = assignment.author,
                        user = assignment.user,
                        visible = assignment.visible,
                        grade = assignment.grade,
                        review = assignment.review,
                        assignmentRoot = assignment.assignmentRoot
                    )
                }
            }
        }
    }

    fun onEvent() {
        TODO()
    }
}