package care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.diary.domain.useCase.ClearEmotionsUC
import care.intouch.app.feature.diary.domain.useCase.GetEmotionDescUC
import care.intouch.app.feature.diary.domain.useCase.GetEmotionUC
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDescriptionEnum
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDescriptionTask
import care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.models.FillingOutDataEvent
import care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.models.FillingOutScreenState
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
class FillingOutViewModel @Inject constructor(
    private val getEmotionUC: GetEmotionUC,
    private val getEmotionDescUC: GetEmotionDescUC,
    private val clearEmotionsUC: ClearEmotionsUC,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        FillingOutScreenState(
            detailsText = "",
            analysisText = "",
            typeText = "",
            sensationsText = "",
        )
    )
    val uiState = _uiState.asStateFlow()
    private val _sharedImageUiState = MutableSharedFlow<ImageVO?>()
    val sharedImageUiState = _sharedImageUiState.asSharedFlow()
    private val _sharedListUiState = MutableSharedFlow<List<EmotionDescriptionTask>>()
    val sharedListUiState = _sharedListUiState.asSharedFlow()

    init {
        getEmotionDescription()
        getEmotion()
    }
    fun onEvent(event: FillingOutDataEvent) {
        when (event) {
            is FillingOutDataEvent.OnAnalysisTextChanged -> updateAnalysisState(event.text)
            is FillingOutDataEvent.OnDetailsTextChanged -> updateDetailsState(event.text)
            is FillingOutDataEvent.OnSensationsTextChanged -> updateSensationsState(event.text)
            is FillingOutDataEvent.OnTypeTextChanged -> updateTypeState(event.text)
            FillingOutDataEvent.OnUpdateStateChanged -> updateStates()
            FillingOutDataEvent.OnTrashClicked -> clearEmotions()
        }
    }

    private fun updateStates() {
        getEmotion()
        getEmotionDescription()
    }

    private fun clearEmotions() {
        viewModelScope.launch {
            clearEmotionsUC.invoke()
            _sharedImageUiState.emit(null)
            _sharedListUiState.emit(listOf())
        }
    }

    private fun getEmotion() {
        var resultEmotion: String
        var resultEmotionImage: ImageVO? = null
        viewModelScope.launch {
            resultEmotion = getEmotionUC.invoke()
            if (resultEmotion.isNotEmpty()) {
                when (resultEmotion) {
                    "TERRIBLE" -> {
                        resultEmotionImage =
                            ImageVO.Resource(care.intouch.uikit.R.drawable.icon_terrible_small_active)
                    }

                    "BAD" -> {
                        resultEmotionImage =
                            ImageVO.Resource(care.intouch.uikit.R.drawable.icon_bad_small_active)
                    }

                    "OKAY" -> {
                        resultEmotionImage =
                            ImageVO.Resource(care.intouch.uikit.R.drawable.icon_okay_small_active)
                    }

                    "GOOD" -> {
                        resultEmotionImage =
                            ImageVO.Resource(care.intouch.uikit.R.drawable.icon_good_small_active)
                    }

                    "GREAT" -> {
                        resultEmotionImage =
                            ImageVO.Resource(care.intouch.uikit.R.drawable.icon_great_small_active)
                    }
                }
            }
            _sharedImageUiState.emit(resultEmotionImage)
        }
    }

    private fun getEmotionDescription() {
        val result: MutableList<EmotionDescriptionTask> = mutableListOf()
        viewModelScope.launch {
            getEmotionDescUC.invoke().forEach {
                when (it) {
                    EmotionDescriptionEnum.Loss -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.loss_clarifying_emotional
                            ), EmotionDescriptionEnum.Loss
                        )
                    )

                    EmotionDescriptionEnum.Humility -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.humility_clarifying_emotional),
                            EmotionDescriptionEnum.Humility
                        )
                    )

                    EmotionDescriptionEnum.Anxiety -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.anxiety_clarifying_emotional
                            ), EmotionDescriptionEnum.Anxiety
                        )
                    )

                    EmotionDescriptionEnum.Embarrassment -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.embarrassment_clarifying_emotional),
                            EmotionDescriptionEnum.Embarrassment
                        )
                    )

                    EmotionDescriptionEnum.Depression -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.depression_clarifying_emotional),
                            EmotionDescriptionEnum.Depression
                        )
                    )

                    EmotionDescriptionEnum.Fear -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.fear_clarifying_emotional
                            ), EmotionDescriptionEnum.Fear
                        )
                    )

                    EmotionDescriptionEnum.Anger -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.excitement_clarifying_emotional
                            ), EmotionDescriptionEnum.Excitement
                        )
                    )

                    EmotionDescriptionEnum.Excitement -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.anger_clarifying_emotional),
                            EmotionDescriptionEnum.Anger
                        )
                    )

                    EmotionDescriptionEnum.Shame -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.disgust_clarifying_emotional
                            ), EmotionDescriptionEnum.Disgust
                        )
                    )

                    EmotionDescriptionEnum.Disgust -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.guilt_clarifying_emotional
                            ), EmotionDescriptionEnum.Guilt
                        )
                    )

                    EmotionDescriptionEnum.Guilt -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.shame_clarifying_emotional
                            ), EmotionDescriptionEnum.Shame
                        )
                    )

                    EmotionDescriptionEnum.Nervousness -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.nervousness_clarifying_emotional),
                            EmotionDescriptionEnum.Nervousness
                        )
                    )

                    EmotionDescriptionEnum.Sadness -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.disappointment_clarifying_emotional
                            ), EmotionDescriptionEnum.Disappointment
                        )
                    )

                    EmotionDescriptionEnum.Exhaustion -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.sadness_clarifying_emotional),
                            EmotionDescriptionEnum.Sadness
                        )
                    )

                    EmotionDescriptionEnum.Impatience -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.impatience_clarifying_emotional),
                            EmotionDescriptionEnum.Impatience
                        )
                    )

                    EmotionDescriptionEnum.Disappointment -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.exhaustion_clarifying_emotional),
                            EmotionDescriptionEnum.Exhaustion
                        )
                    )

                    EmotionDescriptionEnum.Confusion -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.frustration_clarifying_emotional),
                            EmotionDescriptionEnum.Frustration
                        )
                    )

                    EmotionDescriptionEnum.Loneliness -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.confusion_clarifying_emotional),
                            EmotionDescriptionEnum.Confusion
                        )
                    )

                    EmotionDescriptionEnum.Acceptance -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.loneliness_clarifying_emotional),
                            EmotionDescriptionEnum.Loneliness
                        )
                    )

                    EmotionDescriptionEnum.Frustration -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.acceptance_clarifying_emotional),
                            EmotionDescriptionEnum.Acceptance
                        )
                    )

                    EmotionDescriptionEnum.Laziness -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.laziness_clarifying_emotional),
                            EmotionDescriptionEnum.Laziness
                        )
                    )

                    EmotionDescriptionEnum.Pride -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.interest_clarifying_emotional
                            ), EmotionDescriptionEnum.Interest
                        )
                    )

                    EmotionDescriptionEnum.Hope -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.pride_clarifying_emotional
                            ), EmotionDescriptionEnum.Pride
                        )
                    )

                    EmotionDescriptionEnum.Calmness -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.hope_clarifying_emotional),
                            EmotionDescriptionEnum.Hope
                        )
                    )

                    EmotionDescriptionEnum.Gratitude -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.calmness_clarifying_emotional),
                            EmotionDescriptionEnum.Calmness
                        )
                    )

                    EmotionDescriptionEnum.Interest -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.gratitude_clarifying_emotional),
                            EmotionDescriptionEnum.Gratitude
                        )
                    )

                    EmotionDescriptionEnum.Respect -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.happiness_clarifying_emotional
                            ), EmotionDescriptionEnum.Happiness
                        )
                    )

                    EmotionDescriptionEnum.Enthusiasm -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.respect_clarifying_emotional),
                            EmotionDescriptionEnum.Respect
                        )
                    )

                    EmotionDescriptionEnum.Happiness -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.enthusiasm_clarifying_emotional),
                            EmotionDescriptionEnum.Enthusiasm
                        )
                    )

                    EmotionDescriptionEnum.Inspiration -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.satisfaction_clarifying_emotional),
                            EmotionDescriptionEnum.Satisfaction
                        )
                    )

                    EmotionDescriptionEnum.Joy -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.self_love_clarifying_emotional
                            ), EmotionDescriptionEnum.SelfLove
                        )
                    )

                    EmotionDescriptionEnum.Love -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(
                                care.intouch.app.R.string.joy_clarifying_emotional
                            ), EmotionDescriptionEnum.Joy
                        )
                    )

                    EmotionDescriptionEnum.Amazement -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.inspiration_clarifying_emotional),
                            EmotionDescriptionEnum.Inspiration
                        )
                    )

                    EmotionDescriptionEnum.Satisfaction -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.amazement_clarifying_emotional),
                            EmotionDescriptionEnum.Amazement
                        )
                    )

                    EmotionDescriptionEnum.SelfLove -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.euphoria_clarifying_emotional),
                            EmotionDescriptionEnum.Euphoria
                        )
                    )

                    EmotionDescriptionEnum.Euphoria -> result.add(
                        EmotionDescriptionTask(
                            StringVO.Resource(care.intouch.app.R.string.love_clarifying_emotional),
                            EmotionDescriptionEnum.Love
                        )
                    )
                }
            }
            _sharedListUiState.emit(result)
        }
    }
    private fun updateDetailsState(text: String) {
        _uiState.update {
            it.copy(detailsText = text)
        }
    }

    private fun updateAnalysisState(text: String) {
        _uiState.update {
            it.copy(analysisText = text)
        }
    }

    private fun updateSensationsState(text: String) {
        _uiState.update {
            it.copy(sensationsText = text)
        }
    }

    private fun updateTypeState(text: String) {
        _uiState.update {
            it.copy(typeText = text)
        }
    }
}