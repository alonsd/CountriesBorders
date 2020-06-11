package com.countriesborders.util

import android.app.Application
import android.content.Context
import com.countriesborders.util.dependency_injection.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}