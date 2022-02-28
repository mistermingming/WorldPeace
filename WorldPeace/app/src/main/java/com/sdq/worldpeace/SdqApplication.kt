package com.sdq.worldpeace

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SdqApplication:Application() {
    override fun onCreate() {
        super.onCreate()

    }
}