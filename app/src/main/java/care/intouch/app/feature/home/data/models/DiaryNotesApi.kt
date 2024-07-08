package care.intouch.app.feature.home.data.models

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface DiaryNotesApi {
    @GET("/api/v1/diariesNotes-notes/")
    suspend fun getDiaryNotes(@QueryMap queryParameters: Map<String, String>): Response.DiariesNotes

    @GET("/api/v1/diariesNotes-notes/{id}")
    suspend fun deleteDiariesNote(@Path("id") diaryId: Int)

    @POST("/api/v1/diariesNotes-notes/{id}/visible")
    suspend fun setDiariesNoteVisible(@Path("id") diaryId: Int): Response.Regular
}