package care.intouch.app.feature.home.domain

import care.intouch.app.feature.home.domain.models.DiaryEntry

interface DiaryEntryNetworkRepository {
    suspend fun getDiaryEntries(userId: Int): Result<List<DiaryEntry>>
    suspend fun deleteDiaryEntry(diaryNoteId: Int): Result<String>
    suspend fun setDiaryEntryVisible(diaryNoteId: Int): Result<String>
}