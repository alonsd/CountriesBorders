package com.countriesborders.util.dependency_injection

import com.google.gson.Gson
import org.koin.dsl.module

var appModule = module {
    single { Gson() }
}