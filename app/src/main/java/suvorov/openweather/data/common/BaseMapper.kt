package suvorov.openweather.data.common

interface BaseMapper<in A, out B> {
    fun map(type: A): B
}