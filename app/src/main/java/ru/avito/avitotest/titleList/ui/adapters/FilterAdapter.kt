package ru.avito.avitotest.titleList.ui.adapters

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.livermor.delegateadapter.delegate.ViewBindingDelegateAdapter
import ru.avito.avitotest.R
import ru.avito.avitotest.databinding.ItemFilterBinding
import ru.avito.avitotest.databinding.ItemFilterGroupBinding
import ru.avito.avitotest.titleList.model.entities.FilterName

class FilterTitleAdapter :
    ViewBindingDelegateAdapter<String, ItemFilterGroupBinding>(ItemFilterGroupBinding::inflate) {

    override fun isForViewType(item: Any): Boolean = item is String

    override fun ItemFilterGroupBinding.onBind(item: String) {
        this.filterType.text = item
    }

    override fun String.getItemId(): Any = this

}


class FilterAdapter :
    ViewBindingDelegateAdapter<FilterName, ItemFilterBinding>(ItemFilterBinding::inflate) {

    private var checked = arrayListOf<String>()

    private var onItemClick: (FilterName, Boolean) -> Unit = { _, _ ->}

    private fun onItemClick(item: FilterName) {
        if(item.name in checked) {
            checked.remove(item.name)
        } else {
            checked.add(item.name)
        }

        onItemClick(item, item.name in checked)
    }

    private fun getColor(context: Context, colorId: Int) =
        ResourcesCompat.getColor(context.resources, colorId, null)

    override fun isForViewType(item: Any): Boolean = item is FilterName

    override fun ItemFilterBinding.onBind(item: FilterName) {
        this.filterName.text = item.name

        if (item.name in checked) {
            this.root.setCardBackgroundColor(getColor(this.root.context, R.color.black))
            this.filterName.setTextColor(getColor(this.root.context, R.color.white))
        } else {
            this.root.setCardBackgroundColor(getColor(this.root.context, R.color.silver))
            this.filterName.setTextColor(getColor(this.root.context, R.color.black))
        }

        this.root.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun FilterName.getItemId(): Any = name

    fun setCheckedItems(checked: Map<String, List<String>>) {
        checked.values.forEach(this.checked::addAll)
    }

    fun setOnItemClickListener(listener: (FilterName, Boolean) -> Unit) {
        onItemClick = listener
    }
}