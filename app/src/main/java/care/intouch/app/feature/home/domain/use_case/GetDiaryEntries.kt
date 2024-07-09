package care.intouch.app.feature.home.domain.use_case

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.home.domain.models.DiaryEntry
import javax.inject.Inject

interface GetDiaryEntries {
    suspend fun execute(): Resource<List<DiaryEntry>, ErrorEntity>
    class Base @Inject constructor() : GetDiaryEntries {
        override suspend fun execute(): Resource<List<DiaryEntry>, ErrorEntity> {
            TODO("Not yet implemented")
        }
    }
}