package com.example.miniguide.common.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/routes/")
    suspend fun getRoutes(
        @Query("start") startPoint: List<Double>,
        @Query("finish") endPoint: List<Double>,
        @Query("sightsAmount") sightsAmount: Int = 4
    ): RoutesResponse
}