package suvorov.openweather.presentation.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import suvorov.openweather.presentation.di.ViewModelKey
import suvorov.openweather.presentation.ui.home.HomeViewModel
import suvorov.openweather.presentation.ui.daily.DailyForecastViewModel
import suvorov.openweather.presentation.ui.search.SearchViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun searchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DailyForecastViewModel::class)
    abstract fun dailyForecastViewModel(viewModel: DailyForecastViewModel): ViewModel
}