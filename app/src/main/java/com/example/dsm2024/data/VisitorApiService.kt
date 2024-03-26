package com.example.dsm2024.data

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface VisitorApiService {

    @POST("/guest-books/guest-book")
    suspend fun writeVisitorLog(@Body request: WriteVisitorLogRequest)

    @GET("/guest-books/all")
    suspend fun getVisitorLogs(): List<VisitorLogResponse>
}

data class WriteVisitorLogRequest(
    @SerializedName("message") val message: String,
)

data class VisitorLogResponse(
    @SerializedName("message") val message: String,
    @SerializedName("created_at") val createdAt: String,
)
