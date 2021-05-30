package com.example.miniguide.common.remote

import com.google.gson.annotations.SerializedName

data class RoutesResponse (
    @SerializedName("points")
    val points: List<List<Double>>
) : BaseResponse()