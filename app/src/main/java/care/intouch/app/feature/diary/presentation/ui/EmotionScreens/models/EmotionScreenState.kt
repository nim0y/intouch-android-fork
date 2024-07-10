package care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models

data class EmotionScreenState(
    val emotionResult: EmotionDesc,
    val emotionListResult: MutableList<EmotionDescriptionTask>,
    val emotionList: List<EmotionDescriptionTask>,
    val emotion: List<EmotionTask>,
)
