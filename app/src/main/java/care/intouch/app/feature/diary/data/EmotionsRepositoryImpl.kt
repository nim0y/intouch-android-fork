package care.intouch.app.feature.diary.data

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDesc
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDescriptionEnum
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
        withContext(Dispatchers.IO) {
            sharedPreferences.edit { putString(EMOTION_KEY, emotionDesc.name) }
        }
    }

    override suspend fun saveEmotionDesc(list: MutableList<EmotionDescriptionTask>) {
        val result: List<String> = list.map {
            it.emotionDescriptionEnum.name
        }
        withContext(Dispatchers.IO) {
            val data = json.encodeToString(result)
            sharedPreferences.edit { putString(DESC_KEY, data) }
        }
    }

    override suspend fun getEmotion(): String {
        var result: String = ""
        withContext(Dispatchers.IO) {
            result = sharedPreferences.getString(EMOTION_KEY, null).orEmpty()
        }
        Log.d("1", "$result")
        return result
    }

    override suspend fun getEmotionDesc(): List<EmotionDescriptionEnum> {
        var sharedResult = ""
        var result: List<EmotionDescriptionEnum>
        withContext(Dispatchers.IO) {
            sharedResult = sharedPreferences.getString(DESC_KEY, null).orEmpty()
            result = if (sharedResult.isNotEmpty()) {
                json.decodeFromString<List<EmotionDescriptionEnum>>(sharedResult)
            } else {
                listOf()
            }
        }
        Log.d("2", "$result")
        return result
    }

    override suspend fun clearEmotions() {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().remove(EMOTION_KEY).commit()
            sharedPreferences.edit().remove(DESC_KEY).commit()
        }
    }

    companion object {
        const val EMOTION_KEY = "EMOTION"
        const val DESC_KEY = "DESC_KEY"
    }
}