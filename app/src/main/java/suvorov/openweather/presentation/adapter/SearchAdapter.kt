package suvorov.openweather.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import suvorov.openweather.databinding.ItemCitiesListBinding
import suvorov.openweather.data.entity.CityEntity
import suvorov.openweather.presentation.common.OnItemClickListener
import suvorov.openweather.util.setFlat

class SearchAdapter(private val actionListener: OnItemClickListener): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val citiesList = arrayListOf<CityEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemCitiesListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(citiesList[position], actionListener)
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    fun updateList(list: List<CityEntity>) {
        citiesList.clear()
        citiesList.addAll(list)
        notifyDataSetChanged()
    }

    class SearchViewHolder(private val binding: ItemCitiesListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CityEntity, actionListener: OnItemClickListener) {
            binding.apply {
                nameTextView.text = model.name
                countryTextView.text = model.country
                latLonTextView.text = "${model.lat},  ${model.lon.toString()}"
                countryImageView.setFlat(model.country ?: "")
            }

            itemView.setOnClickListener {
                actionListener.onItemClick(model.id ?: 0)
            }
        }
    }
}