package care.intouch.app.feature.questions.data.api

import care.intouch.app.feature.questions.data.models.AssignmentsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AssignmentsApi {
    @GET("/api/v1/assignments/{id}/")
    suspend fun getAssignments(
        @Path("id") id: Int,
    ): AssignmentsDto
}


