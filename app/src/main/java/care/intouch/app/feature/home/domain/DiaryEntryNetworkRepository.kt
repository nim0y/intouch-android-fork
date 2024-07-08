package care.intouch.app.feature.home.domain

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.home.presentation.models.DiaryEntry

interface DiaryEntryNetworkRepository {
    suspend fun getDiaryEntries(userId: Int): Resource<List<DiaryEntry>, ErrorEntity>
    suspend fun deleteDiary(diaryId: Int)
    suspend fun setDiaryVisible(diaryId: Int)
}