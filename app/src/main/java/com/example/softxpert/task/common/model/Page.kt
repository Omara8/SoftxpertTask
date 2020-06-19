package com.example.softxpert.task.common.model

import androidx.paging.PagedList
import java.io.Serializable

data class Page<T>(
    val status: Int?,
    val data: PagedList<T>?
) : Serializable