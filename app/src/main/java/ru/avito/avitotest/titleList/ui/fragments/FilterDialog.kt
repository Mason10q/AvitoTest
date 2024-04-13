package ru.avito.avitotest.titleList.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.avito.avitotest.R
import ru.avito.avitotest.core.ViewModelFactory
import ru.avito.avitotest.core.di.AndroidModule
import ru.avito.avitotest.databinding.DialogFilterBinding
import ru.avito.avitotest.data.network.FilterTypes
import ru.avito.avitotest.titleList.di.DaggerTitleListComponent
import ru.avito.avitotest.titleList.model.entities.FilterName
import ru.avito.avitotest.titleList.model.entities.RangeFilter
import ru.avito.avitotest.titleList.ui.TitleListViewModel
import ru.avito.avitotest.titleList.ui.adapters.FilterAdapter
import ru.avito.avitotest.titleList.ui.adapters.FilterCompositeAdapter
import ru.avito.avitotest.titleList.ui.adapters.RangeFilterAdapter
import ru.avito.avitotest.titleList.ui.adapters.SpaceItemDecorator
import java.time.LocalDateTime
import javax.inject.Inject

private const val COLLAPSED_HEIGHT = 228
class FilterDialog : BottomSheetDialogFragment() {

    private val rangeFilters = listOf(
        RangeFilter(FilterTypes.YEARS.title, FilterTypes.YEARS.serverName, 1970.0f, LocalDateTime.now().year.toFloat(), 1.0f),
        RangeFilter(FilterTypes.RATING.title, FilterTypes.RATING.serverName, 1.0f, 9.0f, 0.1f),
        RangeFilter(FilterTypes.AGE_RATING.title, FilterTypes.AGE_RATING.serverName, 0.0f, 21.0f, 1.0f),
        RangeFilter(FilterTypes.MOVIE_LENGTH.title, FilterTypes.MOVIE_LENGTH.serverName, 10.0f, 300.0f, 1.0f)
    )


    private var onCancelListener = {}

    @Inject lateinit var factory: ViewModelFactory

    private val viewModel by lazy { ViewModelProvider(requireActivity(), factory)[TitleListViewModel::class.java] }

    override fun getTheme() = R.style.BottomDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        inject()

        val binding = DialogFilterBinding.inflate(inflater)

        setupAdapter(binding)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val density = requireContext().resources.displayMetrics.density

        dialog?.let {
            val bottomSheet = it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)

            behavior.peekHeight = (COLLAPSED_HEIGHT * density).toInt()
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        onCancelListener.invoke()
    }

    fun setOnCancelListener(listener: () -> Unit) {
        onCancelListener = listener
    }

    private fun inject() = DaggerTitleListComponent.builder()
        .androidModule(AndroidModule(context ?: throw NullPointerException("Context is null")))
        .build()
        .inject(this)


    private fun setupAdapter(binding: DialogFilterBinding) {
        val filterAdapter = FilterAdapter()
        val rangeFilterAdapter = RangeFilterAdapter()

        val adapter = FilterCompositeAdapter(filterAdapter, rangeFilterAdapter)

        filterAdapter.setCheckedItems(viewModel.getFilterMap())

        filterAdapter.setOnItemClickListener{ filterName, checked ->
            if (checked) {
                viewModel.addFilter(filterName.serverName, filterName.name)
            } else {
                viewModel.deleteFilter(filterName.serverName, filterName.name)
            }

            adapter.notifyItemChanged(filterName)
        }

        binding.filterGroupRecycler.addItemDecoration(SpaceItemDecorator(resources.getDimensionPixelOffset(R.dimen.item_space)))

        rangeFilterAdapter.setOnRangeChangedListener(viewModel::addRangeFilter)

        viewModel.filters.observe(this) { filters ->
            adapter.swapData(filters + rangeFilters)
        }

        binding.filterGroupRecycler.layoutManager = GridLayoutManager(context, 2).apply {
            spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when(adapter.getItem(position)){
                        is String -> 2
                        is RangeFilter -> 2
                        is FilterName -> 1
                        else -> -1
                    }
                }
            }
        }

        binding.filterGroupRecycler.adapter = adapter

    }

}