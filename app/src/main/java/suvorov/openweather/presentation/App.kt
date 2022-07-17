package suvorov.openweather.presentation

import android.app.Application
import suvorov.openweather.presentation.di.component.AppComponent
import suvorov.openweather.presentation.di.component.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}