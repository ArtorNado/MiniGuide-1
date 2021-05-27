package com.example.miniguide.common.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/routes")
    fun getRoutes(
        @Query("start") startPoint: List<Double>,
        @Query("finish") endPoint: List<Double>
    ): RoutesResponse
}