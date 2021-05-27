package com.example.miniguide.routes.data

import com.example.miniguide.common.remote.RoutesResponse
import com.example.miniguide.routes.presentation.pointSearch.model.PointModel
import kotlinx.coroutines.flow.Flow

interface RoutesRepository {

    suspend fun setStartPoint(point: PointModel)

    suspend fun setEndPoint(point: PointModel)

    suspend fun getPointsToVisit(startPoint: PointModel, endPoint: PointModel): RoutesResponse

    fun selectStartPointFlow(): Flow<PointModel>

    fun selectEndPointFlow(): Flow<PointModel>
}