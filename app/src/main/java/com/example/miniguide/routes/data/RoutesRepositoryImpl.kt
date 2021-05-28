package com.example.miniguide.routes.data

import com.example.miniguide.common.remote.ApiService
import com.example.miniguide.common.remote.RoutesResponse
import com.example.miniguide.routes.presentation.pointSearch.model.PointModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoutesRepositoryImpl @Inject constructor(
    private val service: ApiService
) : RoutesRepository {

    private val startPointFlow =
        MutableSharedFlow<PointModel>(replay = 1, 0, BufferOverflow.DROP_OLDEST)

    private val endPointFlow =
        MutableSharedFlow<PointModel>(replay = 1, 0, BufferOverflow.DROP_OLDEST)

    override suspend fun setStartPoint(point: PointModel) = withContext(Dispatchers.Default) {
        startPointFlow.emit(point)
    }

    override suspend fun setEndPoint(point: PointModel) = withContext(Dispatchers.Default) {
        endPointFlow.emit(point)
    }

    override suspend fun getPointsToVisit(
        startPoint: PointModel,
        endPoint: PointModel
    ): RoutesResponse = withContext(Dispatchers.Default) {
        service.getRoutes(
            listOf(startPoint.latitude, startPoint.longitude),
            listOf(endPoint.latitude, endPoint.longitude)
        )
    }

    override fun selectStartPointFlow(): Flow<PointModel> = startPointFlow

    override fun selectEndPointFlow(): Flow<PointModel> = endPointFlow
}