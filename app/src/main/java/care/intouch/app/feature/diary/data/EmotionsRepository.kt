package care.intouch.app.feature.diary.data

import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDesc
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDescriptionEnum
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDescriptionTask

interface EmotionsRepository {
    suspend fun saveEmotion(emotionDesc: EmotionDesc)
    suspend fun saveEmotionDesc(list: MutableList<EmotionDescriptionTask>)
    suspend fun getEmotion(): String
    suspend fun getEmotionDesc(): List<EmotionDescriptionEnum>
    suspend fun clearEmotions()
}