package com.example.softxpert.task.common.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun loadWithGlide(context: Context, url: String, imageView: ImageView?) {
    imageView?.let {
        Glide.with(context).load(url)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)).into(it)
    }
}