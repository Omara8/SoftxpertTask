package com.example.softxpert.task.cars.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.softxpert.task.cars.model.Car
import com.example.softxpert.task.cars.repository.CarsDataSource
import com.example.softxpert.task.cars.repository.CarsRepository

class CarsViewModel : ViewModel() {

    private var carsDataSource: CarsDataSource? = null
    var _carsListResponse: MutableLiveData<PagedList<Car>>? = null
    var _carsListError: MutableLiveData<Boolean>? = null
    private val carsRepository = CarsRepository()

    val carsListResponse: LiveData<PagedList<Car>>
        get() {
            if (_carsListResponse == null)
                _carsListResponse = MutableLiveData()
            return _carsListResponse!!
        }

    val carsListError: LiveData<Boolean>
        get() {
            if (_carsListError == null)
                _carsListError = MutableLiveData()
            return _carsListError!!
        }

    fun getCarsListPage() {
        carsDataSource = CarsDataSource(this)
        carsRepository.getCarsList(1, {
            _carsListResponse?.postValue(it?.data)
            _carsListError?.postValue(false)
        }, {
            _carsListError?.postValue(true)
        })
    }

}