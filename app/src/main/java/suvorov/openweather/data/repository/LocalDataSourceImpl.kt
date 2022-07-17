package suvorov.openweather.data.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Completable
import suvorov.openweather.data.database.WeatherDao
import suvorov.openweather.data.entity.CurrentWeatherEntity
import suvorov.openweather.data.entity.DailyForecastEntity
import suvorov.openweather.data.entity.HourForecastEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val weatherDao: WeatherDao): LocalDataSource {

    override fun getCurrentWeatherFromDatabase(id: Int): LiveData<CurrentWeatherEntity> {
        return weatherDao.getCurrentWeather(id)
    }

    override fun insertCurrentWeatherIntoDatabase(currentWeather: CurrentWeatherEntity): Completable {
        return weatherDao.insertCurrentWeather(currentWeather)
    }

    override fun getHourForecastFromDatabase(id: Int): LiveData<List<HourForecastEntity>> {
        return weatherDao.getHourForecast(id)
    }

    override fun insertHourForecastIntoDatabase(hourForecastList: List<HourForecastEntity>): Completable {
        return weatherDao.insertHourForecast(hourForecastList)
    }

    override fun getDailyForecastFromDatabase(id: Int): LiveData<List<DailyForecastEntity>> {
        return weatherDao.getDailyForecast(id)
    }

    override fun insertDailyForecastIntoDatabase(dailyForecastList: List<DailyForecastEntity>): Completable {
        return weatherDao.insertDailyForecast(dailyForecastList)
    }
}