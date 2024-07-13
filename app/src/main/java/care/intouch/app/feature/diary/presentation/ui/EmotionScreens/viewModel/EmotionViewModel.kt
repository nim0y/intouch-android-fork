package care.intouch.app.feature.diary.presentation.ui.EmotionScreens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.diary.domain.useCase.SaveEmotionDescUC
import care.intouch.app.feature.diary.domain.useCase.SaveEmotionUC
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDataEvent
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDesc
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDescriptionEnum
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDescriptionTask
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionScreenState
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionTask
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EmotionViewModel @Inject constructor(
    private val saveEmotionUC: SaveEmotionUC,
    private val saveEmotionDescUC: SaveEmotionDescUC,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        EmotionScreenState(
            emotion = listOf(),
            emotionList = listOf(),
            emotionListResult = mutableListOf(),
            emotionResult = EmotionDesc.EMPTY,
        )
    )
    val uiState = _uiState.asStateFlow()
    private val _sharedUiState = MutableSharedFlow<Boolean>()
    val sharedUiState = _sharedUiState.asSharedFlow()

    init {
        fetchEmotion()
        fetchDescriptionEmotion()
    }

    fun onEvent(event: EmotionDataEvent) {
        when (event) {
            is EmotionDataEvent.OnEmotionSwap -> {
                createResultEmotion(event.emotionDesc)
            }

            is EmotionDataEvent.OnEmotionDescriptionClicked -> {
                createResultDescriptionList(event.description)
            }

            EmotionDataEvent.OnSaveButtonClicked -> {
                saveEmotions()
                navigateBack()
            }
        }
    }

    private fun saveEmotions() {
        viewModelScope.launch {
            saveEmotionUC.invoke(uiState.value.emotionResult)
            saveEmotionDescUC.invoke(uiState.value.emotionListResult)
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _sharedUiState.emit(true)
        }
    }
    private fun createResultDescriptionList(descriptionTask: EmotionDescriptionTask) {
        if (uiState.value.emotionListResult.contains(descriptionTask)) {
            _uiState.value.emotionListResult.remove(descriptionTask)
        } else {
            _uiState.value.emotionListResult.add(descriptionTask)
        }
        _uiState.update {
            it.copy(emotionListResult = it.emotionListResult)
        }
    }

    private fun createResultEmotion(emotionDesc: EmotionDesc) {
        _uiState.update {
            it.copy(emotionResult = emotionDesc)
        }
    }

    private fun fetchEmotion() {
        val emotionList = listOf(
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_terrible),
                bigImageVO = ImageVO.Resource(R.drawable.icon_terrible_big),
                emotionDesc = EmotionDesc.TERRIBLE
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_bad),
                bigImageVO = ImageVO.Resource(R.drawable.icon_bad_big),
                emotionDesc = EmotionDesc.BAD
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_okay),
                bigImageVO = ImageVO.Resource(R.drawable.icon_okey_big),
                emotionDesc = EmotionDesc.OKAY
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_good),
                bigImageVO = ImageVO.Resource(R.drawable.icon_good_big),
                emotionDesc = EmotionDesc.GOOD
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_great),
                bigImageVO = ImageVO.Resource(R.drawable.icon_great_big),
                emotionDesc = EmotionDesc.GREAT
            ),
        )
        _uiState.update { state ->
            state.copy(emotion = emotionList)
        }
    }

    private fun fetchDescriptionEmotion() {
        val descriptionEmotionList = listOf(
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.loss_clarifying_emotional),
                EmotionDescriptionEnum.Loss
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.humility_clarifying_emotional),
                EmotionDescriptionEnum.Humility
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.anxiety_clarifying_emotional),
                EmotionDescriptionEnum.Anxiety
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.embarrassment_clarifying_emotional),
                EmotionDescriptionEnum.Embarrassment
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.depression_clarifying_emotional),
                EmotionDescriptionEnum.Depression
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.fear_clarifying_emotional),
                EmotionDescriptionEnum.Fear
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.anger_clarifying_emotional),
                EmotionDescriptionEnum.Anger
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.excitement_clarifying_emotional),
                EmotionDescriptionEnum.Excitement
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.shame_clarifying_emotional),
                EmotionDescriptionEnum.Shame
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.disgust_clarifying_emotional),
                EmotionDescriptionEnum.Disgust
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.guilt_clarifying_emotional),
                EmotionDescriptionEnum.Guilt
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.nervousness_clarifying_emotional),
                EmotionDescriptionEnum.Nervousness
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.sadness_clarifying_emotional),
                EmotionDescriptionEnum.Sadness
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.exhaustion_clarifying_emotional),
                EmotionDescriptionEnum.Exhaustion
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.impatience_clarifying_emotional),
                EmotionDescriptionEnum.Impatience
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.disappointment_clarifying_emotional),
                EmotionDescriptionEnum.Disappointment
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.confusion_clarifying_emotional),
                EmotionDescriptionEnum.Confusion
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.loneliness_clarifying_emotional),
                EmotionDescriptionEnum.Loneliness
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.acceptance_clarifying_emotional),
                EmotionDescriptionEnum.Acceptance
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.frustration_clarifying_emotional),
                EmotionDescriptionEnum.Frustration
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.laziness_clarifying_emotional),
                EmotionDescriptionEnum.Laziness
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.pride_clarifying_emotional),
                EmotionDescriptionEnum.Pride
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.hope_clarifying_emotional),
                EmotionDescriptionEnum.Hope
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.calmness_clarifying_emotional),
                EmotionDescriptionEnum.Calmness
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.gratitude_clarifying_emotional),
                EmotionDescriptionEnum.Gratitude
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.interest_clarifying_emotional),
                EmotionDescriptionEnum.Interest
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.respect_clarifying_emotional),
                EmotionDescriptionEnum.Respect
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.enthusiasm_clarifying_emotional),
                EmotionDescriptionEnum.Enthusiasm
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.satisfaction_clarifying_emotional),
                EmotionDescriptionEnum.Satisfaction
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.self_love_clarifying_emotional),
                EmotionDescriptionEnum.SelfLove
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.happiness_clarifying_emotional),
                EmotionDescriptionEnum.Happiness
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.inspiration_clarifying_emotional),
                EmotionDescriptionEnum.Inspiration
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.amazement_clarifying_emotional),
                EmotionDescriptionEnum.Amazement
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.euphoria_clarifying_emotional),
                EmotionDescriptionEnum.Euphoria
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.joy_clarifying_emotional),
                EmotionDescriptionEnum.Joy
            ),
            EmotionDescriptionTask(
                StringVO.Resource(care.intouch.app.R.string.love_clarifying_emotional),
                EmotionDescriptionEnum.Love
            )
        )
        _uiState.update { state ->
            state.copy(emotionList = descriptionEmotionList)
        }
    }
}