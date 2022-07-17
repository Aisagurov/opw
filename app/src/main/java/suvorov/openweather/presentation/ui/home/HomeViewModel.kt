package suvorov.openweather.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import suvorov.openweather.data.entity.CurrentWeatherEntity
import suvorov.openweather.data.entity.HourForecastEntity
import suvorov.openweather.domain.repository.WeatherRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getCurrentWeatherFromDatabase(id: Int): LiveData<CurrentWeatherEntity> {
        return repository.getCurrentWeatherFromDatabase(id)
    }

    fun getCurrentWeatherFromApi(id: Int) {
        compositeDisposable.add(
            repository.getCurrentWeatherFromApi(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { it.message })
        )
    }

    fun getHourForecastFromDatabase(id: Int): LiveData<List<HourForecastEntity>> {
        return repository.getHourForecastFromDatabase(id)
    }

    fun getHourForecastFromApi(id: Int) {
        compositeDisposable.add(
            repository.getHourForecastFromApi(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({},{ it.message })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}