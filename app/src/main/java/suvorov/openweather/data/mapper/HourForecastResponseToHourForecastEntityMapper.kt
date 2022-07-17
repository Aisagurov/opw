package suvorov.openweather.data.mapper

import suvorov.openweather.data.api.model.HourForecastResponse
import suvorov.openweather.data.common.BaseMapper
import suvorov.openweather.data.entity.HourForecastEntity

object HourForecastResponseToHourForecastEntityMapper: BaseMapper<HourForecastResponse, List<HourForecastEntity>> {
    override fun map(type: HourForecastResponse): List<HourForecastEntity> {
        return type.list.orEmpty().map { list ->
            HourForecastEntity(
                list.dt ?: 0,
                type.city?.id,
                type.city?.name,
                list.main?.temp,
                list.weather?.firstOrNull()?.icon,
                list.dtText
            )
        }
    }
}