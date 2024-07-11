package care.intouch.app.feature.home.data.reposytories

import care.intouch.app.feature.home.data.api.DiaryNotesApi
import care.intouch.app.feature.home.data.mappers.HomeMapper
import care.intouch.app.feature.home.domain.DiaryEntryRepository
import care.intouch.app.feature.home.domain.models.DiaryEntry
import timber.log.Timber
import javax.inject.Inject

class DiaryEntryRepositoryImpl @Inject constructor(
    private val diaryNoteApi: DiaryNotesApi,
    private val mapper: HomeMapper
) : DiaryEntryRepository {
    override suspend fun getDiaryEntries(userId: Int): Result<List<DiaryEntry>> {
        try {
            val request = mapper.mapDiaryNoteRequest(
                userId = userId
            )
            val response = diaryNoteApi.getDiaryNotes(
            )
            Timber.tag("response").d(response.toString())
            return Result.success(mapper.mapDiaryEntries(response))

        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }

    override suspend fun deleteDiaryEntry(diaryNoteId: Int): Result<String> {
        try {
            diaryNoteApi.deleteDiaryNote(diaryId = diaryNoteId)
            return Result.success(DELETE_RESPONSE)

        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }

    override suspend fun setDiaryEntryVisible(diaryNoteId: Int): Result<String> {
        try {
            val response = diaryNoteApi.setDiaryNoteVisible(diaryId = diaryNoteId)
            return Result.success(response.massage)

        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }

    companion object {
        const val AMOUNT_OF_DIARY_NOTES_PER_PAGE = 6
        const val AMOUNT_OF_DIARY_NOTES_PAGE = 1
        const val DELETE_RESPONSE = "Diary deleted"
    }
}