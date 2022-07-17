package suvorov.openweather.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import suvorov.openweather.data.entity.CurrentWeatherEntity
import suvorov.openweather.data.entity.DailyForecastEntity
import suvorov.openweather.data.entity.HourForecastEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM current_weather WHERE id = :id")
    fun getCurrentWeather(id: Int): LiveData<CurrentWeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(currentWeather: CurrentWeatherEntity): Completable

    @Query("SELECT * FROM hour_forecast WHERE id = :id")
    fun getHourForecast(id: Int): LiveData<List<HourForecastEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHourForecast(hourForecastList: List<HourForecastEntity>): Completable

    @Query("SELECT * FROM daily_forecast WHERE id = :id")
    fun getDailyForecast(id: Int): LiveData<List<DailyForecastEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyForecast(dailyForecastList: List<DailyForecastEntity>): Completable
}