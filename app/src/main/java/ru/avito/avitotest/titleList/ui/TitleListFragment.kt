package ru.avito.avitotest.titleList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.avito.avitotest.core.di.AndroidModule
import ru.avito.avitotest.databinding.FragmentTitlesListBinding
import ru.avito.avitotest.titleList.di.DaggerTitleListComponent
import javax.inject.Inject

class TitleListFragment: Fragment() {

    private val binding by lazy { FragmentTitlesListBinding.inflate(layoutInflater) }
    private val adapter by lazy { TitleListAdapter(resources) }

    private val filterDialog = FilterDialog()

    @Inject lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy { ViewModelProvider(this, factory)[TitleListViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inject()
        prepareObservers()

        binding.titlesRecycler.adapter = adapter

        viewModel.getTitlesPage()
        viewModel.getAllFilters()

        filterDialog.setOnCancelListener {
            binding.filterButton.isEnabled = true
        }

        binding.filterButton.setOnClickListener{
            filterDialog.show(parentFragmentManager, "")
            binding.filterButton.isEnabled = false
        }

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
        viewModel.titles.observe(viewLifecycleOwner, adapter::addItems)
        viewModel.filters.observe(viewLifecycleOwner) { filters ->
            filterDialog.addFilters(filters)
            binding.filterButton.visibility = View.VISIBLE
        }
    }

}