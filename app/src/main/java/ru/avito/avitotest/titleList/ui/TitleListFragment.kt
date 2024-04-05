package ru.avito.avitotest.titleList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.avito.avitotest.databinding.FragmentTitlesListBinding
import ru.avito.avitotest.titleList.di.DaggerTitleListComponent
import javax.inject.Inject

class TitleListFragment: Fragment() {

    private val binding by lazy { FragmentTitlesListBinding.inflate(layoutInflater) }
    private val adapter by lazy { TitleListAdapter(resources) }

    @Inject lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy { ViewModelProvider(this, factory)[TitleListViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //inject()
        prepareObservers()

        binding.titlesRecycler.adapter = adapter

        viewModel.getTitlesPage()

        return binding.root
    }


//    private fun inject() = DaggerTitleListComponent
//        .builder()
//        .build()
//        .inject(this)

    private fun prepareObservers() {
        viewModel.titles.observe(viewLifecycleOwner, adapter::addItems)
    }

}