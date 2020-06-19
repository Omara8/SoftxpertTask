package com.example.softxpert.task.cars.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.softxpert.task.R
import com.example.softxpert.task.cars.viewmodel.CarsViewModel
import kotlinx.android.synthetic.main.activity_cars.*

class CarsActivity : AppCompatActivity() {

    private var carsViewModel: CarsViewModel? = null
    private val carsAdapter = CarsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cars)
        loadViewModel()
        initSwipeRefresh()
    }

    private fun loadViewModel() {
        carsViewModel = ViewModelProvider(this).get(CarsViewModel::class.java)
        carsViewModel?.let {
            observeViewModel(it)
            it.getCarsListPage()
        }
    }

    private fun observeViewModel(viewModel: CarsViewModel) {
        viewModel.carsListResponse.observe(this, Observer {
            cars_recycler.adapter = carsAdapter
            carsAdapter.submitList(it)
            carsAdapter.notifyDataSetChanged()
        })
        viewModel.carsListError.observe(this, Observer {
            when (it) {
                true -> showError()
                false -> hideState()
            }
        })
    }

    private fun showError() {
        cars_recycler.visibility = View.GONE
        progress_bar.visibility = View.GONE
        error_text.visibility = View.VISIBLE
    }

    private fun hideState() {
        cars_recycler.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
        error_text.visibility = View.GONE
    }

    private fun initSwipeRefresh() {
        swipe_refresh?.setOnRefreshListener {

        }
    }
}