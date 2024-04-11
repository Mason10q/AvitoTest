package ru.avito.avitotest.titleList.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.avito.avitotest.R
import ru.avito.avitotest.core.ViewModelFactory
import ru.avito.avitotest.core.di.AndroidModule
import ru.avito.avitotest.databinding.DialogFilterBinding
import ru.avito.avitotest.titleList.di.DaggerTitleListComponent
import ru.avito.avitotest.titleList.model.entities.Filter
import ru.avito.avitotest.titleList.ui.TitleListViewModel
import ru.avito.avitotest.titleList.ui.adapters.FilterGroupAdapter
import javax.inject.Inject

private const val COLLAPSED_HEIGHT = 228
class FilterDialog : BottomSheetDialogFragment() {


    private var onCancelListener = {}

    @Inject lateinit var factory: ViewModelFactory

    private val viewModel by lazy { ViewModelProvider(requireActivity(), factory)[TitleListViewModel::class.java] }

    private val adapter = FilterGroupAdapter()

    override fun getTheme() = R.style.BottomDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        inject()

        val binding = DialogFilterBinding.inflate(inflater)

        adapter.setOnClickListener{ key, value, checked ->
            if(checked) {
                viewModel.deleteFilter(key, value)
            } else {
                viewModel.addFilter(key, value)
            }
        }

        adapter.setChecked(viewModel.getFilterMap())

        binding.filterGroupRecycler.adapter = adapter

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

    fun addFilters(filters: List<Filter>) {
        adapter.setItems(filters)
    }

    fun setOnCancelListener(listener: () -> Unit) {
        onCancelListener = listener
    }

    private fun inject() = DaggerTitleListComponent.builder()
        .androidModule(AndroidModule(context ?: throw NullPointerException("Context is null")))
        .build()
        .inject(this)
}