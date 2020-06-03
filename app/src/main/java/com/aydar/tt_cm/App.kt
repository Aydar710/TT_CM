package com.aydar.tt_cm

import android.app.Application
import com.aydar.tt_cm.data.di.repositoryModule
import com.aydar.tt_cm.featurepersons.di.featurePersonsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                repositoryModule,
                featurePersonsModule
            )
        }

    }
}