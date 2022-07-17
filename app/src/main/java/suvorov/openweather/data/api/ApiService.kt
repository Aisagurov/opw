package suvorov.openweather.data.api

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import suvorov.openweather.data.api.model.CurrentWeatherResponse
import suvorov.openweather.data.api.model.DailyForecastResponse
import suvorov.openweather.data.api.model.HourForecastResponse
import suvorov.openweather.data.api.model.SearchWeatherResponse

interface ApiService {
    @GET("weather?")
    fun getCurrentWeather(
        @Query("id") id: Int,
        @Query("lang") lang: String = "ru"
    ): Observable<CurrentWeatherResponse>

    @GET("find?")
    fun getSearchWeather(
        @Query("q") city: String,
    ): Single<SearchWeatherResponse>

    @GET("forecast?")
    fun getHourForecast(
        @Query("id") id: Int,
        @Query("cnt") hours: String = "12",
        @Query("lang") lang: String = "ru"
    ): Single<HourForecastResponse>

    @GET("forecast/daily?")
    fun getDailyForecast(
        @Query("id") id: String,
        @Query("cnt") days: Int = 16,
        @Query("lang") lang: String = "ru"
    ): Single<DailyForecastResponse>
}