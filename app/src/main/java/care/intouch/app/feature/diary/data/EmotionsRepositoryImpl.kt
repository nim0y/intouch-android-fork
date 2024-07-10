package care.intouch.app.feature.diary.data

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDesc
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDescriptionTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class EmotionsRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val json: Json,
) : EmotionsRepository {
    override suspend fun saveEmotion(emotionDesc: EmotionDesc) {
        Log.d("1", "$emotionDesc")
        withContext(Dispatchers.IO) {
            sharedPreferences.edit { putString(EMOTION_KEY, emotionDesc.name) }
        }
    }

    override suspend fun saveEmotionDesc(list: MutableList<EmotionDescriptionTask>) {
        val result: List<String> = list.map {
            it.text.toString()
        }
        Log.d("1", "$result")
        withContext(Dispatchers.IO) {
            val data = json.encodeToString(result)
            sharedPreferences.edit { putString(DESC_KEY, data) }
        }
    }

    companion object {
        const val EMOTION_KEY = "EMOTION"
        const val DESC_KEY = "DESC_KEY"
    }
}