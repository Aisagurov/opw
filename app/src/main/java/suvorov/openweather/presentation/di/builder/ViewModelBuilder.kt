package suvorov.openweather.presentation.di.builder

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import suvorov.openweather.presentation.di.ViewModelFactory

@Module
abstract class ViewModelBuilder {
    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}