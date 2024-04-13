package ru.avito.avitotest.titleList.ui.adapters

import com.google.android.material.slider.RangeSlider
import com.livermor.delegateadapter.delegate.ViewBindingDelegateAdapter
import ru.avito.avitotest.databinding.ItemRangeFilterBinding
import ru.avito.avitotest.titleList.model.entities.RangeFilter

class RangeFilterAdapter: ViewBindingDelegateAdapter<RangeFilter, ItemRangeFilterBinding>(ItemRangeFilterBinding::inflate) {

    private var onRangeChangedListener: (String, String) -> Unit = {_,_ ->}

    fun setOnRangeChangedListener(listener: (String, String) -> Unit) {
        onRangeChangedListener = listener
    }

    override fun isForViewType(item: Any): Boolean  = item is RangeFilter

    override fun ItemRangeFilterBinding.onBind(item: RangeFilter) {
        with(this) {
            title.text = item.title
            slider.valueFrom = item.valueFrom
            slider.valueTo = item.valueTo
            slider.values = item.startValues
            slider.stepSize = item.stepSize

            this.slider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
                override fun onStartTrackingTouch(p0: RangeSlider) {}

                override fun onStopTrackingTouch(p0: RangeSlider) {
                    onRangeChangedListener(item.serverName, "${p0.values[0]}-${p0.values[1]}")
                }
            })
        }
    }


    override fun RangeFilter.getItemId(): Any = this.title

}