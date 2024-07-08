package care.intouch.app.feature.home.domain.use_case

import javax.inject.Inject

interface DiaryEntriesInteractor {
    fun deleteDiaryEntry(id: Int)
    fun shareDiaryEntryWithDoctor(id: Int)
    class Base @Inject constructor() : DiaryEntriesInteractor {
        override fun deleteDiaryEntry(id: Int) {
            TODO("Not yet implemented")
        }

        override fun shareDiaryEntryWithDoctor(id: Int) {
            TODO("Not yet implemented")
        }
    }
}