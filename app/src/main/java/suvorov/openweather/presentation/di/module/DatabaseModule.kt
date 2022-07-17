package suvorov.openweather.presentation.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import suvorov.openweather.data.database.WeatherDao
import suvorov.openweather.data.database.WeatherDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDao(database: WeatherDatabase): WeatherDao {
        return database.weatherDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): WeatherDatabase {
        return Room.databaseBuilder(context, WeatherDatabase::class.java, "WeatherDatabase").build()
    }
}