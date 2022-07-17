package suvorov.openweather.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_forecast")
data class DailyForecastEntity(
    @PrimaryKey
    val dt: Int,
    val id: Int?,
    val name: String?,
    val sunrise: Int?,
    val sunset: Int?,
    val tempMin: Double?,
    val tempMax: Double?,
    val tempDay: Double?,
    val tempNight: Double?,
    val tempEve: Double?,
    val tempMorn: Double?,
    val feelsLikeDay: Double?,
    val feelsLikeNight: Double?,
    val feelsLikeEve: Double?,
    val feelsLikeMorn: Double?,
    val pressure: Int?,
    val humidity: Int?,
    val description: String?,
    val icon: String?,
    val winterSpeed: Double?
)