package care.intouch.app.feature.home.data.api

import care.intouch.app.feature.home.data.models.Responses
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface DiaryNotesApi {
    @GET("/api/v1/diariesNotes-notes/")
    suspend fun getDiaryNotes(@QueryMap queryParameters: Map<String, String>): Responses.DiariesNotes

    @GET("/api/v1/diariesNotes-notes/{id}")
    suspend fun deleteDiaryNote(@Path("id") diaryId: Int)

    @POST("/api/v1/diariesNotes-notes/{id}/visible")
    suspend fun setDiaryNoteVisible(@Path("id") diaryId: Int): Responses.Regular
}