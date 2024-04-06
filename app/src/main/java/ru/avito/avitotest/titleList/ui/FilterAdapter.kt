package ru.avito.avitotest.titleList.ui

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abstracttesting.adapter.BaseAdapter
import ru.avito.avitotest.databinding.ItemFilterBinding
import ru.avito.avitotest.databinding.ItemFilterGroupBinding
import ru.avito.avitotest.titleList.model.entities.Filter

class FilterGroupAdapter: BaseAdapter<Filter, ItemFilterGroupBinding>(ItemFilterGroupBinding::inflate) {

    override fun bindView(binding: ItemFilterGroupBinding, item: Filter, context: Context) {
        val adapter = FilterAdapter()
        binding.filterType.text = item.title
        binding.filterRecycler.adapter = adapter
        binding.filterRecycler.layoutManager = GridLayoutManager(context, 3)
        adapter.addItems(item.filterNames)
    }


    inner class FilterAdapter: BaseAdapter<String, ItemFilterBinding>(ItemFilterBinding::inflate) {
        override fun bindView(binding: ItemFilterBinding, item: String, context: Context) {
            binding.filterName.text = item
        }
    }

}