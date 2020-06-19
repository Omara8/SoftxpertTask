package com.example.softxpert.task.cars.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.softxpert.task.R
import com.example.softxpert.task.cars.model.Car

class CarsAdapter(private val context: Context) :
    PagedListAdapter<Car, CarViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.car_item, parent, false)
        return CarViewHolder(view, context)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Car>() {
            override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean =
                oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean =
                oldItem == newItem
        }
    }

}