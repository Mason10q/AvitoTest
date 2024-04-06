package ru.avito.avitotest.titleList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.avito.avitotest.R
import ru.avito.avitotest.databinding.DialogFilterBinding
import ru.avito.avitotest.titleList.model.entities.Filter

private const val COLLAPSED_HEIGHT = 228
class FilterDialog : BottomSheetDialogFragment() {

    private val adapter = FilterGroupAdapter()
    override fun getTheme() = R.style.BottomDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DialogFilterBinding.inflate(inflater)

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

    fun addFilters(filters: List<Filter>) {
        adapter.addItems(filters)
    }

}