package com.studiomk.randomcatfacts.presentation.di

import android.app.Application
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(applicationModule))
        }
    }
}