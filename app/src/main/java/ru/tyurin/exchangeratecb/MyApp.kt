package ru.tyurin.exchangeratecb

import android.app.Application
import ru.tyurin.exchangeratecb.di.AppComponent
import ru.tyurin.exchangeratecb.di.DaggerAppComponent

class MyApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .build()
    }
}