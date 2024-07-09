package care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models

import care.intouch.uikit.common.ImageVO

data class EmotionScreenState(
    val emotionResult: ImageVO?,
    val emotionListResult: MutableList<EmotionDescriptionTask>,
    val emotionList: List<EmotionDescriptionTask>,
    val emotion: List<EmotionTask>,
)
