package suvorov.openweather.presentation.ui.daily

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import suvorov.openweather.data.entity.DailyForecastEntity
import suvorov.openweather.domain.repository.WeatherRepository
import javax.inject.Inject

class DailyForecastViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getDailyForecastFromDatabase(id: Int): LiveData<List<DailyForecastEntity>> {
        return repository.getDailyForecastFromDatabase(id)
    }

    fun getDailyForecastFromApi(id: String) {
        compositeDisposable.add(
            repository.getDailyForecastFromApi(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({},{ it.message })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}