package care.intouch.app.feature.home.data.reposytories

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.home.data.api.DiaryNotesApi
import care.intouch.app.feature.home.data.mappers.HomeExceptionMapper
import care.intouch.app.feature.home.data.mappers.HomeMapper
import care.intouch.app.feature.home.domain.DiaryEntryNetworkRepository
import care.intouch.app.feature.home.domain.models.DiaryEntry

class DiaryEntryNetworkRepositoryImp(
    private val diaryNoteApi: DiaryNotesApi,
    private val mapper: HomeMapper,
    private val exceptionMapper: HomeExceptionMapper
) : DiaryEntryNetworkRepository {
    override suspend fun getDiaryEntries(userId: Int): Resource<List<DiaryEntry>, ErrorEntity> {
        try {
            val request = mapper.mapDiaryNoteRequest(
                userId = userId,
                limit = AMOUNT_OF_DIARY_NOTES_PER_PAGE,
                page = AMOUNT_OF_DIARY_NOTES_PAGE
            )
            val response = diaryNoteApi.getDiaryNotes(
                request
            )
            return Resource.Success(mapper.mapDiaryEntries(response))

        } catch (exception: Exception) {
            val error = exceptionMapper.handleException(exception)
            return Resource.Error(error)
        }
    }

    override suspend fun deleteDiaryEntry(diaryId: Int): Resource<String, ErrorEntity> {
        try {
            diaryNoteApi.deleteDiaryNote(diaryId = diaryId)
            return Resource.Success(DELETE_RESPONSE)

        } catch (exception: Exception) {
            val error = exceptionMapper.handleException(exception)
            return Resource.Error(error)
        }
    }

    override suspend fun setDiaryEntryVisible(diaryId: Int): Resource<String, ErrorEntity> {
        try {
            val response = diaryNoteApi.setDiaryNoteVisible(diaryId = diaryId)
            return Resource.Success(response.massage)

        } catch (exception: Exception) {
            val error = exceptionMapper.handleException(exception)
            return Resource.Error(error)
        }
    }

    companion object {
        const val AMOUNT_OF_DIARY_NOTES_PER_PAGE = 6
        const val AMOUNT_OF_DIARY_NOTES_PAGE = 1
        const val DELETE_RESPONSE = "Diary deleted"
    }
}