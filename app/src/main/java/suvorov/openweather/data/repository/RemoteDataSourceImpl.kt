package suvorov.openweather.data.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import suvorov.openweather.data.api.ApiService
import suvorov.openweather.data.api.model.CurrentWeatherResponse
import suvorov.openweather.data.api.model.DailyForecastResponse
import suvorov.openweather.data.api.model.HourForecastResponse
import suvorov.openweather.data.api.model.SearchWeatherResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val service: ApiService): RemoteDataSource {

    override fun getCurrentWeather(id: Int): Observable<CurrentWeatherResponse> {
        return service.getCurrentWeather(id)
    }

    override fun getSearchWeather(city: String): Single<SearchWeatherResponse> {
        return service.getSearchWeather(city)
    }

    override fun getHourForecast(id: Int): Single<HourForecastResponse> {
        return service.getHourForecast(id)
    }

    override fun getDailyForecast(id: String): Single<DailyForecastResponse> {
        return service.getDailyForecast(id)
    }
}