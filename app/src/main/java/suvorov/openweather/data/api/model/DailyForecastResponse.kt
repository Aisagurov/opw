package suvorov.openweather.data.api.model

import com.google.gson.annotations.SerializedName

data class DailyForecastResponse(
    val city: CityApi?,
    val list: List<DailyForecastApi>?
)

data class CityApi(
    val id: Int?,
    val name: String?
)

data class DailyForecastApi(
    val dt: Int?,
    val sunrise: Int?,
    val sunset: Int?,
    val temp: TempApi?,
    @SerializedName("feels_like") val feelsLike: FeelsLikeApi?,
    val pressure: Int?,
    val humidity: Int?,
    val weather: List<WeatherApi>?,
    val speed: Double?
)

data class TempApi(
    val day: Double?,
    @SerializedName("min") val tempMin: Double?,
    @SerializedName("max") val tempMax: Double?,
    val night: Double?,
    val eve: Double?,
    val morn: Double?
)

data class FeelsLikeApi(
    val day: Double?,
    val night: Double?,
    val eve: Double?,
    val morn: Double?
)