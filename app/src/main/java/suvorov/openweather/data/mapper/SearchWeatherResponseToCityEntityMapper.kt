package suvorov.openweather.data.mapper

import suvorov.openweather.data.api.model.SearchWeatherResponse
import suvorov.openweather.data.common.BaseMapper
import suvorov.openweather.data.entity.CityEntity

object SearchWeatherResponseToCityEntityMapper: BaseMapper<SearchWeatherResponse, List<CityEntity>> {
    override fun map(type: SearchWeatherResponse): List<CityEntity> {
        return type.list.orEmpty().map {
            CityEntity(
                it.id ?: 0,
                it.name,
                it.coord?.lon,
                it.coord?.lat,
                it.sys?.country
            )
        }
    }
}