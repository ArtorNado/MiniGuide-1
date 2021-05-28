package com.example.miniguide.routes.domain

import android.util.Log
import com.example.miniguide.common.remote.RoutesResponse
import com.example.miniguide.map.data.MapRepository
import com.example.miniguide.routes.data.RoutesRepository
import com.example.miniguide.routes.presentation.pointSearch.model.PointModel
import com.mapbox.geojson.Point
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RoutesInteractorImpl @Inject constructor(
    private val routesRepository: RoutesRepository,
    private val mapRepository: MapRepository
) : RoutesInteractor {

    override suspend fun setStartPoint(point: PointModel) {
        routesRepository.setStartPoint(point)
    }

    override suspend fun setEndPoint(point: PointModel) {
        routesRepository.setEndPoint(point)
    }

    override suspend fun createRoute(startPoint: PointModel, endPoint: PointModel) {
        val listOfPoints: MutableList<Point> = mutableListOf()
        val placesToVisit = routesRepository.getPointsToVisit(startPoint, endPoint).points
//        val call = placesToVisit
//         try {
//            val response = call.execute().body()
//            when (response.isSuccessful) {
//                true -> Either.Right((response.body()!!))
//                false -> Either.Left(response.parseError())
//            }
//        } catch (exception: Throwable) {
//            Either.Left(Failure.ServerError)
//        }
//        placesToVisit.enqueue(object : Callback<RoutesResponse> {
//            override fun onResponse(call: Call<RoutesResponse>, response: Response<UserLoginDetailModel>) {
//
//                loginResponseInterface.onSuccess(loginDetail?.status.toString(),loginDetail ?. errorcode.toString(), loginDetail)
//                Log.d("WebServices", "" + loginDetail)
//
//            }
//
//            override fun onFailure(call: Call<UserLoginDetailModel>, t: Throwable) {
//                // loginResponseInterface.onSuccess("","", loginDetail);
//                loginResponseInterface.onFailure(t)
//            }
//        })

        placesToVisit.forEach {
            listOfPoints.add(Point.fromLngLat(it[0], it[1]))
        }
        mapRepository.createRoute(listOfPoints)
    }

    override fun startPointFlow(): Flow<PointModel> = routesRepository.selectStartPointFlow()

    override fun endPointFlow(): Flow<PointModel> = routesRepository.selectEndPointFlow()
}