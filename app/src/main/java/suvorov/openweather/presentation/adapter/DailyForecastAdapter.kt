package suvorov.openweather.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import suvorov.openweather.data.entity.DailyForecastEntity
import suvorov.openweather.databinding.ItemDailyForecastBinding
import suvorov.openweather.util.setImage
import suvorov.openweather.util.unixTimestampToDateTimeString
import suvorov.openweather.util.unixTimestampToDayMonthString
import suvorov.openweather.util.unixTimestampToDayWeekString

class DailyForecastAdapter: RecyclerView.Adapter<DailyForecastAdapter.ForecastViewHolder>() {

    private val forecastList = arrayListOf<DailyForecastEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(ItemDailyForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    fun updateList(list: List<DailyForecastEntity>) {
        forecastList.clear()
        forecastList.addAll(list)
        notifyDataSetChanged()
    }

    class ForecastViewHolder(private val binding: ItemDailyForecastBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: DailyForecastEntity) {

            when(forecast.dt.unixTimestampToDayWeekString()) {
                "Mon" -> binding.dayWeekTextView.text = "Пн"
                "Tue" -> binding.dayWeekTextView.text = "Вт"
                "Wed" -> binding.dayWeekTextView.text = "Ср"
                "Thu" -> binding.dayWeekTextView.text = "Чт"
                "Fri" -> binding.dayWeekTextView.text = "Пт"
                "Sat" -> binding.dayWeekTextView.text = "Сб"
                "Sun" -> binding.dayWeekTextView.text = "Вс"
            }

            when(forecast.dt.unixTimestampToDayMonthString()) {
                "01" -> binding.dayMonthTextView.text = "января"
                "02" -> binding.dayMonthTextView.text = "февраля"
                "03" -> binding.dayMonthTextView.text = "марта"
                "04" -> binding.dayMonthTextView.text = "апреля"
                "05" -> binding.dayMonthTextView.text = "мая"
                "06" -> binding.dayMonthTextView.text = "июня"
                "07" -> binding.dayMonthTextView.text = "июля"
                "08" -> binding.dayMonthTextView.text = "августа"
                "09" -> binding.dayMonthTextView.text = "сентября"
                "10" -> binding.dayMonthTextView.text = "октября"
                "11" -> binding.dayMonthTextView.text = "ноября"
                "12" -> binding.dayMonthTextView.text = "декабря"
            }

            binding.apply {
                dateTextView.text = forecast.dt.unixTimestampToDateTimeString()
                iconImageView.setImage(forecast.icon ?: "")
                descriptionTextView.text = forecast.description
                tempMorningTextView.text = "${forecast.tempMorn?.toInt()}°C"
                tempDayTextView.text = "${forecast.tempDay?.toInt()}°C"
                tempEveningTextView.text = "${forecast.tempEve?.toInt()}°C"
                tempNightTextView.text = "${forecast.tempNight?.toInt()}°C"
            }
        }
    }
}