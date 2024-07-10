package care.intouch.app.feature.home.data.reposytories

import care.intouch.app.feature.home.data.api.DiaryNotesApi
import care.intouch.app.feature.home.data.mappers.HomeMapper
import care.intouch.app.feature.home.domain.DiaryEntryNetworkRepository
import care.intouch.app.feature.home.domain.models.DiaryEntry
import javax.inject.Inject

class DiaryEntryNetworkRepositoryImp @Inject constructor(
    private val diaryNoteApi: DiaryNotesApi,
    private val mapper: HomeMapper
) : DiaryEntryNetworkRepository {
    override suspend fun getDiaryEntries(userId: Int): Result<List<DiaryEntry>> {
        try {
            val request = mapper.mapDiaryNoteRequest(
                userId = userId,
                limit = AMOUNT_OF_DIARY_NOTES_PER_PAGE,
                page = AMOUNT_OF_DIARY_NOTES_PAGE
            )
            val response = diaryNoteApi.getDiaryNotes(
                request
            )
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