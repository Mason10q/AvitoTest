package ru.avito.avitotest.title.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.avito.avitotest.core.ViewModelFactory
import ru.avito.avitotest.databinding.FragmentReviewsBinding
import ru.avito.avitotest.title.di.DaggerTitleComponent
import javax.inject.Inject

class ReviewsFragment: Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by lazy { ViewModelProvider(this, factory)[TitleViewModel::class.java] }

    private val binding by lazy { FragmentReviewsBinding.inflate(layoutInflater) }

    private val reviewAdapter = ReviewAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject()

        viewModel.titleId = arguments?.getInt("movieid") ?: 0

        binding.root.adapter = reviewAdapter
        getReviewFlow()

        return binding.root
    }

    private fun inject() = DaggerTitleComponent.builder()
        .build()
        .inject(this)

    private fun getReviewFlow() {
        lifecycleScope.launch {
            viewModel.getReviewDataFlow().collect {
                reviewAdapter.submitData(it)
            }
        }
    }

}