package com.prafullm.jetcomposer.app

import android.app.Application
import timber.log.Timber

class JetComposerApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}