package suvorov.openweather.data.mapper

import suvorov.openweather.data.api.model.CurrentWeatherResponse
import suvorov.openweather.data.common.BaseMapper
import suvorov.openweather.data.entity.CurrentWeatherEntity

object CurrentWeatherResponseToCurrentWeatherEntityMapper: BaseMapper<CurrentWeatherResponse, CurrentWeatherEntity> {
    override fun map(type: CurrentWeatherResponse): CurrentWeatherEntity {
        return type.let {
            CurrentWeatherEntity(
                it.id ?: 0,
                it.name,
                it.weather?.firstOrNull()?.description,
                it.weather?.firstOrNull()?.icon,
                it.main?.temp,
                it.main?.feelsLike,
                it.main?.tempMin,
                it.main?.tempMax,
                it.main?.pressure,
                it.main?.humidity,
                it.visibility,
                it.wind?.speed,
                it.wind?.deg,
                it.dt,
                it.sys?.sunrise,
                it.sys?.sunset
            )
        }
    }
}