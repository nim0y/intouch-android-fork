package care.intouch.app.feature.home.domain

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.home.domain.models.DiaryEntry

interface DiaryEntryNetworkRepository {
    suspend fun getDiaryEntries(userId: Int): Resource<List<DiaryEntry>, ErrorEntity>
    suspend fun deleteDiaryEntry(diaryId: Int): Resource<String, ErrorEntity>
    suspend fun setDiaryEntryVisible(diaryId: Int): Resource<String, ErrorEntity>
}