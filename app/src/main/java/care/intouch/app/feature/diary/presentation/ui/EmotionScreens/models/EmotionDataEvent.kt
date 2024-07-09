package care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models

import care.intouch.uikit.common.ImageVO

sealed class EmotionDataEvent {
    data class OnEmotionDescriptionClicked(val description: EmotionDescriptionTask) :
        EmotionDataEvent()

    data class OnEmotionSwap(val imageVO: ImageVO) : EmotionDataEvent()
}