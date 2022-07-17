package suvorov.openweather.presentation.di.module

import dagger.Module
import dagger.Provides
import suvorov.openweather.data.api.ApiService
import suvorov.openweather.data.database.WeatherDao
import suvorov.openweather.data.repository.LocalDataSourceImpl
import suvorov.openweather.data.repository.RemoteDataSourceImpl
import suvorov.openweather.data.repository.WeatherRepositoryImpl
import suvorov.openweather.data.repository.LocalDataSource
import suvorov.openweather.data.repository.RemoteDataSource
import suvorov.openweather.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(weatherDao: WeatherDao): LocalDataSource {
        return LocalDataSourceImpl(weatherDao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: ApiService): RemoteDataSource {
        return RemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): WeatherRepository {

        return WeatherRepositoryImpl(localDataSource, remoteDataSource)
    }
}