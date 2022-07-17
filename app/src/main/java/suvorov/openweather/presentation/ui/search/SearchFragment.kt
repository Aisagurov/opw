package suvorov.openweather.presentation.ui.search

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import suvorov.openweather.R
import suvorov.openweather.databinding.FragmentSearchBinding
import suvorov.openweather.presentation.App
import suvorov.openweather.presentation.adapter.SearchAdapter
import suvorov.openweather.presentation.common.OnItemClickListener
import suvorov.openweather.util.Constants
import javax.inject.Inject

class SearchFragment: Fragment(), SearchView.OnQueryTextListener, OnItemClickListener {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SearchViewModel by viewModels(factoryProducer = { viewModelFactory })

    private val searchAdapter = SearchAdapter(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.injectSearchFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.citySearchView.setOnQueryTextListener(this)

        binding.citySearchView.findViewById<TextView>(
            androidx.appcompat.R.id.search_src_text).setTextColor(Color.WHITE)

        observeViewModel()

        initializeView()

        binding.backStackImageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeViewModel() {
        viewModel.citiesList.observe(viewLifecycleOwner) {
            searchAdapter.updateList(it)
        }
    }

    private fun initializeView() {
        binding.citiesListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            viewModel.getSearchWeatherFromApi(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onItemClick(id: Int) {
        val bundle = bundleOf(Constants.CITY_ID to id)
        findNavController().navigate(
            R.id.action_searchFragment_to_homeFragment, bundle, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}