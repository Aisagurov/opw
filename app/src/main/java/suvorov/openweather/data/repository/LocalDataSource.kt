package suvorov.openweather.data.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Completable
import suvorov.openweather.data.entity.CurrentWeatherEntity
import suvorov.openweather.data.entity.DailyForecastEntity
import suvorov.openweather.data.entity.HourForecastEntity

interface LocalDataSource {

    fun getCurrentWeatherFromDatabase(id: Int): LiveData<CurrentWeatherEntity>

    fun insertCurrentWeatherIntoDatabase(currentWeather: CurrentWeatherEntity): Completable

    fun getHourForecastFromDatabase(id: Int): LiveData<List<HourForecastEntity>>

    fun insertHourForecastIntoDatabase(hourForecastList: List<HourForecastEntity>): Completable

    fun getDailyForecastFromDatabase(id: Int): LiveData<List<DailyForecastEntity>>

    fun insertDailyForecastIntoDatabase(dailyForecastList: List<DailyForecastEntity>): Completable
}