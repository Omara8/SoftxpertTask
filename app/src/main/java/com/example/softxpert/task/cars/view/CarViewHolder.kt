package com.example.softxpert.task.cars.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.softxpert.task.R
import com.example.softxpert.task.cars.model.Car
import com.example.softxpert.task.common.utils.loadWithGlide

class CarViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

    private var carImage: ImageView? = null
    private var carBrand: TextView? = null
    private var carState: TextView? = null
    private var carConstructionYear: TextView? = null

    init {
        carImage = itemView.findViewById(R.id.car_image)
        carBrand = itemView.findViewById(R.id.car_brand)
        carState = itemView.findViewById(R.id.is_used)
        carConstructionYear = itemView.findViewById(R.id.construction_year)
    }

    fun bindTo(car: Car?) {
        car?.let {
            it.imageUrl?.let { imageUrl ->
                loadWithGlide(context, imageUrl, carImage)
            }
            carBrand?.text = it.brand
            when (it.isUsed) {
                true -> carState?.text = context.getString(R.string.used)
                false -> carState?.text = context.getString(R.string.new_car)
            }
            carConstructionYear?.text = it.constructionYear
        }
    }

}