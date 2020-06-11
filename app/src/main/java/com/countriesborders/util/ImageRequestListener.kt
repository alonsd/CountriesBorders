package com.countriesborders.util

import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition

class ImageRequestListener(private val callback: Callback?) : RequestListener<Drawable> {

    interface Callback {
        fun onFailure(message: String?)
        fun onSuccess(countryName: String)
    }

    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
        callback?.onFailure(e?.message)
        return false
    }

    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
        callback?.onSuccess(dataSource.toString())
        target!!.onResourceReady(resource!!, DrawableCrossFadeTransition(1000, isFirstResource))
        return true
    }

}