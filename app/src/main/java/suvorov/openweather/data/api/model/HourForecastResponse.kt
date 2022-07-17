package suvorov.openweather.data.api.model

import com.google.gson.annotations.SerializedName

data class HourForecastResponse(
    val list: List<HourForecastApi>?,
    val city: CityApi?
)

data class HourForecastApi(
    val dt: Int?,
    val main: MainApi?,
    val weather: List<WeatherApi>?,
    @SerializedName("dt_txt") val dtText: String?
)