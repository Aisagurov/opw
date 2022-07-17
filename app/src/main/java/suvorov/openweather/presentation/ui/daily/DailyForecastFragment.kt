package suvorov.openweather.presentation.ui.daily

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import suvorov.openweather.databinding.FragmentDailyForecastBinding
import suvorov.openweather.presentation.App
import suvorov.openweather.presentation.adapter.DailyForecastAdapter
import suvorov.openweather.util.Constants
import javax.inject.Inject

class DailyForecastFragment: Fragment() {
    private var _binding: FragmentDailyForecastBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: DailyForecastViewModel by viewModels(factoryProducer = { viewModelFactory })

    private val forecastAdapter = DailyForecastAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.injectDailyForecastFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentDailyForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityId = checkNotNull(arguments?.getInt(Constants.CITY_ID))

        observeViewModel(cityId)

        initializeView()

        viewModel.getDailyForecastFromApi(cityId.toString())

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeViewModel(id: Int) {
        viewModel.getDailyForecastFromDatabase(id).observe(viewLifecycleOwner) {
            binding.nameTextView.text = it.firstOrNull()?.name
            forecastAdapter.updateList(it)
        }
    }

    private fun initializeView() {
        binding.dailyForecastRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = forecastAdapter
        }
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}