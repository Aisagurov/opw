package suvorov.openweather.presentation.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import suvorov.openweather.presentation.di.builder.ViewModelBuilder
import suvorov.openweather.presentation.di.module.*
import suvorov.openweather.presentation.ui.home.HomeFragment
import suvorov.openweather.presentation.ui.daily.DailyForecastFragment
import suvorov.openweather.presentation.ui.search.SearchFragment
import javax.inject.Singleton

@Component(modules = [
    ApiModule::class,
    ContextModule::class,
    DatabaseModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    ViewModelBuilder::class
])

@Singleton
interface AppComponent {
    fun injectHomeFragment(fragment: HomeFragment)
    fun injectSearchFragment(fragment: SearchFragment)
    fun injectDailyForecastFragment(fragment: DailyForecastFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}