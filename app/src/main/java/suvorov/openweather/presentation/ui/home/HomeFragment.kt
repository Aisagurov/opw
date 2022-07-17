package suvorov.openweather.presentation.ui.home

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import suvorov.openweather.R
import suvorov.openweather.databinding.FragmentHomeBinding
import suvorov.openweather.presentation.App
import suvorov.openweather.presentation.adapter.HourForecastAdapter
import suvorov.openweather.util.Constants
import suvorov.openweather.util.convertingDegrees
import suvorov.openweather.util.setImage
import suvorov.openweather.util.unixTimestampToTimeString
import javax.inject.Inject

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by viewModels(factoryProducer = { viewModelFactory })

    private val forecastAdapter = HourForecastAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.injectHomeFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //524901 default Moscow
        val cityId = arguments?.getInt(Constants.CITY_ID) ?: 524901

        viewModel.getCurrentWeatherFromApi(cityId)

        viewModel.getHourForecastFromApi(cityId)

        observeViewModel(cityId)

        initializeView()

        binding.searchImageView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            val runnable = Runnable {
                viewModel.getCurrentWeatherFromApi(cityId)
                viewModel.getHourForecastFromApi(cityId)
                binding.swipeRefreshLayout.isRefreshing = false
            }
            Handler(Looper.getMainLooper()).postDelayed(runnable, 1500)
        }

        binding.forecastButton.setOnClickListener {
            val bundle = bundleOf(Constants.CITY_ID to cityId)

            findNavController().navigate(
                R.id.action_homeFragment_to_dailyForecastFragment, bundle, null)
        }
    }

    private fun observeViewModel(id: Int) {
        observeCurrentWeather(id)
        observeHourForecast(id)
    }

    private fun observeCurrentWeather(id: Int) {
        viewModel.getCurrentWeatherFromDatabase(id).observe(viewLifecycleOwner) {
            binding.apply {
                nameTextView.text = it?.name
                iconImageView.setImage(it?.icon ?: "")
                descriptionTextView.text = it?.description
                tempTextView.text = "${it?.temp?.toInt()}°C"
                feelsLikeTextView.text = "ощущается как ${it?.feelsLike?.toInt()}°C"
                humidityTextView.text = "${it?.humidity}%"
                pressureTextView.text = "${it?.pressure?.times(0.75)?.toInt()}мм.рт.ст."
                visibilityTextView.text = "${it?.visibility?.div(1000)}км"
                windTextView.text = "${it?.windSpeed}м/с"
                degImageView.convertingDegrees(it?.windDeg)
                sunriseTextView.text = it?.sunrise?.unixTimestampToTimeString()
                sunsetTextView.text = it?.sunset?.unixTimestampToTimeString()
            }
        }
    }

    private fun observeHourForecast(id: Int) {
        viewModel.getHourForecastFromDatabase(id).observe(viewLifecycleOwner) {
            forecastAdapter.updateList(it)
        }
    }

    private fun initializeView() {
        binding.hourForecastRecyclerView.apply {
            val forecastLinearLayoutManager = LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false)

            layoutManager = forecastLinearLayoutManager
            adapter = forecastAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}