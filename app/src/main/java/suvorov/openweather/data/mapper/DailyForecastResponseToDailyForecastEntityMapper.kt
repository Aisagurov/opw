package suvorov.openweather.data.mapper

import suvorov.openweather.data.api.model.DailyForecastResponse
import suvorov.openweather.data.common.BaseMapper
import suvorov.openweather.data.entity.DailyForecastEntity

object DailyForecastResponseToDailyForecastEntityMapper: BaseMapper<DailyForecastResponse, List<DailyForecastEntity>> {
    override fun map(type: DailyForecastResponse): List<DailyForecastEntity> {
        return type.list.orEmpty().map { list ->
            DailyForecastEntity(
                list.dt ?: 0,
                type.city?.id,
                type.city?.name,
                list.sunrise,
                list.sunset,
                list.temp?.tempMin,
                list.temp?.tempMax,
                list.temp?.day,
                list.temp?.night,
                list.temp?.eve,
                list.temp?.morn,
                list.feelsLike?.day,
                list.feelsLike?.night,
                list.feelsLike?.eve,
                list.feelsLike?.morn,
                list.pressure,
                list.humidity,
                list.weather?.firstOrNull()?.description,
                list.weather?.firstOrNull()?.icon,
                list.speed
            )
        }
    }
}