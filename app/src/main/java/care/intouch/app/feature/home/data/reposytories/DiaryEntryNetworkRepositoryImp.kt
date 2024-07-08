package care.intouch.app.feature.home.data.reposytories

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.home.data.mappers.HomeExceptionMapper
import care.intouch.app.feature.home.data.mappers.HomeMapper
import care.intouch.app.feature.home.data.models.DiaryNotesApi
import care.intouch.app.feature.home.data.models.Response
import care.intouch.app.feature.home.domain.DiaryEntryNetworkRepository
import care.intouch.app.feature.home.presentation.models.DiaryEntry

class DiaryEntryNetworkRepositoryImp(
    private val diariesNoteApi: DiaryNotesApi,
    private val mapper: HomeMapper,
    private val exceptionMapper: HomeExceptionMapper
) : DiaryEntryNetworkRepository {
    override suspend fun getDiaryEntries(userId: Int): Resource<List<DiaryEntry>, ErrorEntity> {
        try {
            val response = diariesNoteApi.getDiaryNotes()
            return Resource.Success(mapper.mapDiaryEntries(response))

        } catch (exception: Exception) {
            val error = exceptionMapper.handleException(exception)
            return Resource.Error(error)
        }
    }

    override suspend fun deleteDiary(diaryId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun setDiaryVisible(diaryId: Int) {
        TODO("Not yet implemented")
    }
}