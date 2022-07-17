package suvorov.openweather.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import suvorov.openweather.data.entity.HourForecastEntity
import suvorov.openweather.databinding.ItemHourForecastBinding
import suvorov.openweather.util.setImage

class HourForecastAdapter: RecyclerView.Adapter<HourForecastAdapter.HourForecastViewHolder>() {

    private val forecastList = arrayListOf<HourForecastEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourForecastViewHolder {
        return HourForecastViewHolder(ItemHourForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HourForecastViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    fun updateList(list: List<HourForecastEntity>) {
        forecastList.clear()
        forecastList.addAll(list)
        notifyDataSetChanged()
    }

    class HourForecastViewHolder(private val binding: ItemHourForecastBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: HourForecastEntity) {
            binding.apply {
                hourTextView.text = forecast.dtText?.substring(11..15)
                iconImageView.setImage(forecast.icon ?: "")
                tempTextView.text = "${forecast.temp?.toInt()}°C"
            }
        }
    }
}