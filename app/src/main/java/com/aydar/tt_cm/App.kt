package com.aydar.tt_cm

import android.app.Application
import com.aydar.tt_cm.data.di.repositoryModule
import com.aydar.tt_cm.featurepersons.di.featurePersonsModule
import com.onesignal.OneSignal
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()

        initOneSignal()

        initAppMetrica()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                repositoryModule,
                featurePersonsModule
            )
        }
    }

    private fun initOneSignal() {
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .setNotificationOpenedHandler(NotificationOpenedHandler(this))
            .init()
    }

    private fun initAppMetrica() {
        val config = YandexMetricaConfig.newConfigBuilder(BuildConfig.APP_METRICA_APP_KEY).build()
        YandexMetrica.activate(applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(this)
    }
}