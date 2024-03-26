package com.example.dsm2024.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit

object VisitorRepository {
    private val retrofit: Retrofit by lazy { RetrofitClient.getClient() }
    private val visitorApiService: VisitorApiService by lazy {
        retrofit.create(VisitorApiService::class.java)
    }

    fun writeVisitorLog(message: String) {
        CoroutineScope(Dispatchers.IO).launch {
            visitorApiService.writeVisitorLog(
                request = WriteVisitorLogRequest(
                    message = message,
                ),
            )
        }
    }

    fun getAllVisitorLogs(): List<VisitorLogResponse> =
        runBlocking(Dispatchers.IO) { visitorApiService.getVisitorLogs() }
}
