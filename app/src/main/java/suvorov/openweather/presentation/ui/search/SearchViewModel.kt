package suvorov.openweather.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import suvorov.openweather.data.entity.CityEntity
import suvorov.openweather.domain.repository.WeatherRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _citiesList = MutableLiveData<List<CityEntity>>()
    val citiesList: LiveData<List<CityEntity>> = _citiesList

    fun getSearchWeatherFromApi(city: String) {
        compositeDisposable.add(
            repository.getSearchWeatherFromApi(city).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ _citiesList.postValue(it) },{ it.message })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}