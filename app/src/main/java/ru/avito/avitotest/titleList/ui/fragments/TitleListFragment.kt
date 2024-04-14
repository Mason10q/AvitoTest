package ru.avito.avitotest.titleList.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.avito.avitotest.R
import ru.avito.avitotest.core.di.AndroidModule
import ru.avito.avitotest.databinding.FragmentTitlesListBinding
import ru.avito.avitotest.titleList.di.DaggerTitleListComponent
import ru.avito.avitotest.titleList.ui.TitleListViewModel
import ru.avito.avitotest.titleList.ui.adapters.FooterLoadStateAdapter
import ru.avito.avitotest.titleList.ui.adapters.TitleListAdapter
import javax.inject.Inject

class TitleListFragment : Fragment() {

    private val binding by lazy { FragmentTitlesListBinding.inflate(layoutInflater) }
    private val adapter = TitleListAdapter()

    private val filterDialog = FilterDialog()

    @Inject lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy { ViewModelProvider(requireActivity(), factory)[TitleListViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inject()
        prepareObservers()

        binding.titlesRecycler.adapter = adapter.withLoadStateFooter(FooterLoadStateAdapter())

        viewModel.getAllFilters()
        launchTitles()

        filterDialog.setOnCancelListener {
            binding.filterButton.isEnabled = true
            launchTitles()
        }

        binding.filterButton.setOnClickListener{
            filterDialog.show(parentFragmentManager, "")
            binding.filterButton.isEnabled = false
        }

        adapter.setOnItemCLickListener { id ->
            findNavController().navigate(R.id.titleFragment, bundleOf("movieid" to id))
        }

        adapter.addLoadStateListener { loadState ->
            if(loadState.prepend.endOfPaginationReached){
                if(adapter.itemCount < 1) {
                    binding.emptyList.root.visibility = View.VISIBLE
                } else {
                    binding.emptyList.root.visibility = View.GONE
                }
            }
        }

        binding.searchButton.setOnInputListener(::launchSearch)

        return binding.root
    }


    private fun inject() = DaggerTitleListComponent
        .builder()
        .androidModule(AndroidModule(context ?: throw NullPointerException("Context is null")))
        .build()
        .apply {
            inject(this@TitleListFragment)
            inject(adapter)
        }

    private fun prepareObservers() {
        viewModel.filters.observe(viewLifecycleOwner) {
            binding.filterButton.visibility = View.VISIBLE
        }
    }

    private fun launchTitles() {
        lifecycleScope.launch {
            viewModel.getTitlePageFlow().collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun launchSearch(query: String) {
        lifecycleScope.launch {
            viewModel.search(query).collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

}