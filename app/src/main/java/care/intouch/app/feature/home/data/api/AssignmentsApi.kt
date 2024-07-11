package care.intouch.app.feature.home.data.api

import care.intouch.app.feature.home.data.models.AssignmentsResponse
import care.intouch.app.feature.home.data.models.RegularResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AssignmentsApi {
    @GET("/api/v1/assignments-client/")
    suspend fun getClientsAssignments(): AssignmentsResponse

    @GET("/api/v1/assignments-client/{id}/clear/")
    suspend fun clearAssignment(@Path("id") assignmentId: Int): RegularResponse

    @POST("/api/v1/assignments-client/{id}/visible/")
    suspend fun setAssignmentVisible(@Path("id") assignmentId: Int): RegularResponse
}