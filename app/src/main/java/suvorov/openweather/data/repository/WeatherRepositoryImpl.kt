package suvorov.openweather.data.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import suvorov.openweather.data.api.model.CurrentWeatherResponse
import suvorov.openweather.data.api.model.DailyForecastResponse
import suvorov.openweather.data.api.model.HourForecastResponse
import suvorov.openweather.data.entity.CurrentWeatherEntity
import suvorov.openweather.data.entity.DailyForecastEntity
import suvorov.openweather.data.entity.HourForecastEntity
import suvorov.openweather.data.entity.CityEntity
import suvorov.openweather.data.mapper.CurrentWeatherResponseToCurrentWeatherEntityMapper
import suvorov.openweather.data.mapper.DailyForecastResponseToDailyForecastEntityMapper
import suvorov.openweather.data.mapper.HourForecastResponseToHourForecastEntityMapper
import suvorov.openweather.data.mapper.SearchWeatherResponseToCityEntityMapper
import suvorov.openweather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
    ): WeatherRepository {

    //Current weather
    override fun getCurrentWeatherFromDatabase(id: Int): LiveData<CurrentWeatherEntity> {
        return localDataSource.getCurrentWeatherFromDatabase(id)
    }

    override fun getCurrentWeatherFromApi(id: Int): Observable<CurrentWeatherResponse> {
        return remoteDataSource.getCurrentWeather(id).doOnNext {
            val customWeather = CurrentWeatherResponseToCurrentWeatherEntityMapper.map(it)
            localDataSource.insertCurrentWeatherIntoDatabase(customWeather).subscribe()
        }
    }

    //Search weather
    override fun getSearchWeatherFromApi(city: String): Single<List<CityEntity>> {
        return remoteDataSource.getSearchWeather(city).map {
            SearchWeatherResponseToCityEntityMapper.map(it)
        }
    }

    //Hour forecast
    override fun getHourForecastFromDatabase(id: Int): LiveData<List<HourForecastEntity>> {
        return localDataSource.getHourForecastFromDatabase(id)
    }

    override fun getHourForecastFromApi(id: Int): Single<HourForecastResponse> {
        return remoteDataSource.getHourForecast(id).doOnSuccess {
            val customHourForecast = HourForecastResponseToHourForecastEntityMapper.map(it)
            localDataSource.insertHourForecastIntoDatabase(customHourForecast).subscribe()
        }
    }

    //Daily forecast
    override fun getDailyForecastFromDatabase(id: Int): LiveData<List<DailyForecastEntity>> {
        return localDataSource.getDailyForecastFromDatabase(id)
    }

    override fun getDailyForecastFromApi(id: String): Single<DailyForecastResponse> {
        return remoteDataSource.getDailyForecast(id).doOnSuccess {
            val customDailyForecast = DailyForecastResponseToDailyForecastEntityMapper.map(it)
            localDataSource.insertDailyForecastIntoDatabase(customDailyForecast).subscribe()
        }
    }
}