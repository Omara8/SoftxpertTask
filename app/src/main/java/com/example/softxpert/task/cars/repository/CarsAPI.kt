package com.example.softxpert.task.cars.repository

import com.example.softxpert.task.cars.model.Car
import com.example.softxpert.task.common.model.Page
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CarsAPI {

    @GET("api/v1/cars?page={page}")
    fun getCarsList(@Path("page") page: Int): Observable<Page<Car>>

}