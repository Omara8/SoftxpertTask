package com.example.softxpert.task.cars.repository

import androidx.paging.PageKeyedDataSource
import com.example.softxpert.task.cars.model.Car
import com.example.softxpert.task.cars.viewmodel.CarsViewModel

class CarsDataSource(private val carsViewModel: CarsViewModel) : PageKeyedDataSource<Int, Car>() {

    private val carsRepository = CarsRepository()
    private val initialPage = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Car>
    ) {
        carsRepository.getCarsList(initialPage, {
            carsViewModel._carsListResponse?.postValue(it?.data)
            carsViewModel._carsListError?.postValue(false)
        }, {
            carsViewModel._carsListError?.postValue(true)
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Car>) {
        carsRepository.getCarsList(params.key + 1, {
            carsViewModel._carsListResponse?.postValue(it?.data)
            carsViewModel._carsListError?.postValue(false)
        }, {
            carsViewModel._carsListError?.postValue(true)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Car>) {
        carsRepository.getCarsList(params.key - 1, {
            carsViewModel._carsListResponse?.postValue(it?.data)
            carsViewModel._carsListError?.postValue(false)
        }, {
            carsViewModel._carsListError?.postValue(true)
        })
    }

}