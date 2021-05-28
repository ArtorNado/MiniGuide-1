package com.example.miniguide.common.remote

import android.telecom.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/routes")
    suspend fun getRoutes(
        @Query("start") startPoint: List<Double>,
        @Query("finish") endPoint: List<Double>
    ): RoutesResponse
}