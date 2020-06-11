package com.countriesborders.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import com.countriesborders.database.entities.CountryEntity

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

/*
Sorts country page data according to area
 */
fun DataSource.Factory<Int, CountryEntity>.sortBy(comparator: Comparator<in CountryEntity>): DataSource.Factory<Int, CountryEntity> {
    return mapByPage { list ->
        list.sortedWith(comparator)
    }
}