package suvorov.openweather.data.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import suvorov.openweather.data.api.model.CurrentWeatherResponse
import suvorov.openweather.data.api.model.DailyForecastResponse
import suvorov.openweather.data.api.model.HourForecastResponse
import suvorov.openweather.data.api.model.SearchWeatherResponse

interface RemoteDataSource {

    fun getCurrentWeather(id: Int): Observable<CurrentWeatherResponse>

    fun getSearchWeather(city: String): Single<SearchWeatherResponse>

    fun getHourForecast(id: Int): Single<HourForecastResponse>

    fun getDailyForecast(id: String): Single<DailyForecastResponse>
}