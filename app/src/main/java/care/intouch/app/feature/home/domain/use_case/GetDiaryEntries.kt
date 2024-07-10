package care.intouch.app.feature.home.domain.use_case

import care.intouch.app.feature.home.domain.DiaryEntryNetworkRepository
import care.intouch.app.feature.home.domain.models.DiaryEntry
import javax.inject.Inject

interface GetDiaryEntries {
    suspend fun execute(userId: Int): Result<List<DiaryEntry>>
    class Base @Inject constructor(private val repository: DiaryEntryNetworkRepository) :
        GetDiaryEntries {
        override suspend fun execute(userId: Int): Result<List<DiaryEntry>> {
            return repository.getDiaryEntries(userId = userId)
        }
    }
}