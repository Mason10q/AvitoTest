package ru.avito.avitotest.title.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.aemerse.slider.model.CarouselItem
import ru.avito.avitotest.core.ViewModelFactory
import ru.avito.avitotest.databinding.FragmentTitleBinding
import ru.avito.avitotest.databinding.FragmentTitlesListBinding
import ru.avito.avitotest.title.di.DaggerTitleComponent
import javax.inject.Inject

class TitleFragment: Fragment() {

    private val binding by lazy { FragmentTitleBinding.inflate(layoutInflater) }

    @Inject lateinit var factory: ViewModelFactory

    private val viewModel by lazy { ViewModelProvider(this, factory)[TitleViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject()

        viewModel.titleId = arguments?.getInt("movieid") ?: 0
        binding.carousel.registerLifecycle(lifecycle)


        viewModel.getTitle()
        viewModel.getPosters()

        viewModel.title.observe(viewLifecycleOwner) {title ->
            binding.name.text = title.name
            binding.description.text = title.description
        }

        viewModel.posters.observe(viewLifecycleOwner) { posters ->
            binding.carousel.setData(posters.map {
                Log.d("asd", it.url)
                CarouselItem(it.url)
            })
        }

        return binding.root
    }



    private fun inject() = DaggerTitleComponent.builder()
        .build()
        .inject(this)


}