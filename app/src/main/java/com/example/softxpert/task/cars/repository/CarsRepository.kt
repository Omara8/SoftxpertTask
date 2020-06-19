package com.example.softxpert.task.cars.repository

import com.example.softxpert.task.cars.model.Car
import com.example.softxpert.task.common.model.Page
import com.example.softxpert.task.common.network.RetrofitBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CarsRepository() {

    private val retrofit = RetrofitBuilder.getRetrofit()
    private val client = retrofit.create(CarsAPI::class.java)

    fun getCarsList(
        page: Int,
        successCallback: (Page<Car>?) -> Unit,
        failureCallback: () -> Unit
    ): Disposable? {
        val carsObservable: Observable<Page<Car>> = client.getCarsList(page)
        return carsObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    successCallback(result)
                },
                {
                    failureCallback()
                }
            )
    }

}