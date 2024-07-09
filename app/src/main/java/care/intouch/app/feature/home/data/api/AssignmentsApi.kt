package care.intouch.app.feature.home.data.api

import care.intouch.app.feature.home.data.models.Responses
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface AssignmentsApi {
    @GET("/api/v1/assignments-client")
    suspend fun getClientsAssignments(@QueryMap queryParameters: Map<String, String>): Responses.Assignments

    @GET("/api/v1/assignments-client/{id}/clear/")
    suspend fun clearAssignment(@Path("id") assignmentId: Int): Responses.Regular

    @POST("/api/v1/assignments-client/{id}/visible")
    suspend fun setAssignmentVisible(@Path("id") assignmentId: Int): Responses.Regular
}