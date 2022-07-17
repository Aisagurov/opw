package suvorov.openweather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import suvorov.openweather.data.entity.CurrentWeatherEntity
import suvorov.openweather.data.entity.DailyForecastEntity
import suvorov.openweather.data.entity.HourForecastEntity

@Database(entities =[
    CurrentWeatherEntity::class,
    DailyForecastEntity::class,
    HourForecastEntity::class
    ], version = 1, exportSchema = false)

abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}