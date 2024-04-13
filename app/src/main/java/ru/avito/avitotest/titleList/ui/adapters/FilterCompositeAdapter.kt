package ru.avito.avitotest.titleList.ui.adapters

import android.util.Log
import com.livermor.delegateadapter.delegate.CompositeDelegateAdapter
import okhttp3.internal.filterList
import ru.avito.avitotest.titleList.model.entities.Filter
import ru.avito.avitotest.titleList.model.entities.RangeFilter
import java.text.FieldPosition

class FilterCompositeAdapter(filterAdapter: FilterAdapter, rangeFilterAdapter: RangeFilterAdapter): CompositeDelegateAdapter(
    filterAdapter,
    rangeFilterAdapter,
    FilterTitleAdapter()
) {

    override fun swapData(data: List<Any>) {

        val _data = arrayListOf<Any>()

        _data.addAll(data.filterIsInstance<RangeFilter>())

        data.filterIsInstance<Filter>().forEach{ filter ->
            _data.add(filter.title)
            _data.addAll(filter.filterNames)
        }

        super.swapData(_data)
    }

    fun notifyItemChanged(item: Any) {
        notifyItemChanged(adapterState.data.indexOf(item))
    }

    fun getItem(position: Int) = adapterState.data[position]

}