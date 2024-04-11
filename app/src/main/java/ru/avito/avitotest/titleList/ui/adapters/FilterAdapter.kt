package ru.avito.avitotest.titleList.ui.adapters

import android.content.Context
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import ru.avito.avitotest.core.BaseAdapter
import ru.avito.avitotest.R
import ru.avito.avitotest.databinding.ItemFilterBinding
import ru.avito.avitotest.databinding.ItemFilterGroupBinding
import ru.avito.avitotest.titleList.model.entities.Filter

class FilterGroupAdapter :
    BaseAdapter<Filter, ItemFilterGroupBinding>(ItemFilterGroupBinding::inflate) {

    private var onItemClick: (String, String, Boolean) -> Unit = { _, _, _ -> }

    private var allChecked = mapOf<String, List<String>>()

    override fun bindView(binding: ItemFilterGroupBinding, item: Filter) {
        val adapter = FilterAdapter()
        adapter.serverName = item.serverName
        binding.filterType.text = item.title
        binding.filterRecycler.adapter = adapter
        binding.filterRecycler.layoutManager = GridLayoutManager(binding.root.context, 2)
        adapter.setItems(item.filterNames)
        binding.filterRecycler.addItemDecoration(
            SpaceItemDecorator(binding.root.context.resources.getDimensionPixelOffset(R.dimen.item_space))
        )

        if(!allChecked[item.serverName].isNullOrEmpty()) {
            adapter.checked.addAll(allChecked[item.serverName] ?: listOf())
        }
    }

    fun setChecked(checked: Map<String, List<String>>) {
        this.allChecked = checked
    }

    fun setOnClickListener(listener: (String, String, Boolean) -> Unit) {
        onItemClick = listener
    }

    inner class FilterAdapter : BaseAdapter<String, ItemFilterBinding>(ItemFilterBinding::inflate) {

        val checked = ArrayList<String>()
        var serverName = ""

        override fun bindView(binding: ItemFilterBinding, item: String) {
            binding.filterName.text = item
            if (item in checked) {
                binding.root.setCardBackgroundColor(getColor(binding.root.context, R.color.black))
                binding.filterName.setTextColor(getColor(binding.root.context, R.color.white))
            } else {
                binding.root.setCardBackgroundColor(getColor(binding.root.context, R.color.silver))
                binding.filterName.setTextColor(getColor(binding.root.context, R.color.black))
            }
        }

        override fun onClick(view: View, item: String, position: Int) {
            onItemClick(serverName, item, item in checked)
            if (item in checked) {
                checked.remove(item)
                notifyItemChanged(position)
            } else {
                checked.add(item)
                notifyItemChanged(position)
            }
        }

        private fun getColor(context: Context, colorId: Int) =
            ResourcesCompat.getColor(context.resources, colorId, null)
    }

}