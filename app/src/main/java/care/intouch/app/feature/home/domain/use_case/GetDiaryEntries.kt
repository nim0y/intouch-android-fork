package care.intouch.app.feature.home.domain.use_case

import care.intouch.app.feature.home.domain.DiaryEntryRepository
import care.intouch.app.feature.home.domain.models.DiaryEntry
import timber.log.Timber
import javax.inject.Inject

interface GetDiaryEntries {
    suspend fun execute(userId: Int): Result<List<DiaryEntry>>
    class Base @Inject constructor(private val repository: DiaryEntryRepository) :
        GetDiaryEntries {
        override suspend fun execute(userId: Int): Result<List<DiaryEntry>> {
            val response = repository.getDiaryEntries(userId = userId)
            Timber.tag("response").d(response.toString())
            return response
        }
    }
}