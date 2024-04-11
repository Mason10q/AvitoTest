package ru.avito.avitotest.titleList.ui.adapters

import android.content.Context
import com.google.android.material.slider.RangeSlider
import ru.avito.avitotest.core.BaseAdapter
import ru.avito.avitotest.databinding.ItemRangeFilterBinding
import ru.avito.avitotest.network.FilterTypes
import ru.avito.avitotest.titleList.model.entities.RangeFilter
import java.time.LocalDateTime

class RangeFilterAdapter: BaseAdapter<RangeFilter, ItemRangeFilterBinding>(ItemRangeFilterBinding::inflate) {

    private val onSliderTouchListener = object : RangeSlider.OnSliderTouchListener {
        override fun onStartTrackingTouch(p0: RangeSlider) {}

        override fun onStopTrackingTouch(p0: RangeSlider) {
            onRangeChangedListener(p0.values[0], p0.values[1])
        }
    }

    private var onRangeChangedListener: (Float, Float) -> Unit = {_,_ ->}

    init {
        this.addItems(
            listOf(
                RangeFilter(FilterTypes.YEARS.title, FilterTypes.YEARS.serverName, 1970.0f, LocalDateTime.now().year.toFloat(), 1.0f),
                RangeFilter(FilterTypes.RATING.title, FilterTypes.RATING.serverName, 1.0f, 9.0f, 0.1f),
                RangeFilter(FilterTypes.AGE_RATING.title, FilterTypes.AGE_RATING.serverName, 0.0f, 21.0f, 1.0f),
                RangeFilter(FilterTypes.MOVIE_LENGTH.title, FilterTypes.MOVIE_LENGTH.serverName, 10.0f, 300.0f, 1.0f)
            )
        )
    }

    override fun bindView(binding: ItemRangeFilterBinding, item: RangeFilter) {
        with(binding) {
            title.text = item.title
            slider.valueFrom = item.valueFrom
            slider.valueTo = item.valueTo
            slider.values = item.startValues
            slider.stepSize = item.stepSize
        }
    }


    fun setOnRangeChangedListener(listener: (Float, Float) -> Unit) {
        onRangeChangedListener = listener
    }

}