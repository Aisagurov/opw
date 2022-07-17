package suvorov.openweather.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val description: String?,
    val icon: String?,
    val temp: Double?,
    val feelsLike: Double?,
    val tempMin: Double?,
    val tempMax: Double?,
    val pressure: Int?,
    val humidity: Int?,
    val visibility: Int?,
    val windSpeed: Double?,
    val windDeg: Int?,
    val dt: Long?,
    val sunrise: Int?,
    val sunset: Int?
)