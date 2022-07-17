package suvorov.openweather.data.api.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    val id: Int?,
    val name: String?,
    val weather: List<WeatherApi>?,
    val main: MainApi?,
    val visibility: Int?,
    val wind: WindApi?,
    val dt: Long?,
    val sys: SysApi?
)

data class WeatherApi(
    val description: String?,
    val icon: String?
)

data class MainApi(
    val temp: Double?,
    @SerializedName("feels_like") val feelsLike: Double?,
    @SerializedName("temp_min") val tempMin: Double?,
    @SerializedName("temp_max") val tempMax: Double?,
    val pressure: Int?,
    val humidity: Int?
)

data class WindApi(
    val speed: Double?,
    val deg: Int?
)

data class SysApi(
    val country: String?,
    val sunrise: Int?,
    val sunset: Int?
)