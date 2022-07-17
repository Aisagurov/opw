package suvorov.openweather.data.api.model

data class SearchWeatherResponse(
    val list: List<SearchWeatherApi>?
)

data class SearchWeatherApi(
    val id: Int?,
    val name: String?,
    val coord: CoordApi?,
    val sys: SysSearchApi?
)

data class SysSearchApi(
    val country: String?
)

data class CoordApi(
    val lat: Double?,
    val lon: Double?
)