package suvorov.openweather.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hour_forecast")
data class HourForecastEntity(
    @PrimaryKey
    val dt: Int,
    val id: Int?,
    val name: String?,
    val temp: Double?,
    val icon: String?,
    val dtText: String?
)