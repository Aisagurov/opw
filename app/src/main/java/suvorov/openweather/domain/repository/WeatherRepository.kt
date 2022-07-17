package suvorov.openweather.domain.repository

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

interface WeatherRepository {

    fun getCurrentWeatherFromDatabase(id: Int): LiveData<CurrentWeatherEntity>

    fun getCurrentWeatherFromApi(id: Int): Observable<CurrentWeatherResponse>

    fun getSearchWeatherFromApi(city: String): Single<List<CityEntity>>

    fun getHourForecastFromDatabase(id: Int): LiveData<List<HourForecastEntity>>

    fun getHourForecastFromApi(id: Int): Single<HourForecastResponse>

    fun getDailyForecastFromDatabase(id: Int): LiveData<List<DailyForecastEntity>>

    fun getDailyForecastFromApi(id: String): Single<DailyForecastResponse>
}