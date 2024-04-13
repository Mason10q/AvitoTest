package ru.avito.avitotest.titleList.ui

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.children
import ru.avito.avitotest.databinding.SearchViewBinding


class CustomSearchView(context: Context, attributes: AttributeSet): FrameLayout(context, attributes) {

    private val binding = SearchViewBinding.inflate(LayoutInflater.from(context))
    private var onInputListener: (String) -> Unit = {}
    private val handler = Handler(Looper.getMainLooper())


    init {
        binding.searchButton.setOnClickListener{
            binding.searchButton.visibility = GONE
            binding.root.layoutParams.width = 600
            binding.searchBar.visibility = VISIBLE

            binding.searchBar.setIconifiedByDefault(true)
            binding.searchBar.isIconified = false
            binding.searchBar.requestFocus()
        }



        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideSearchView()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                handler.removeCallbacksAndMessages(null)

                handler.postDelayed({
                    onInputListener(binding.searchBar.query.toString().trim())
                }, 1000)

                return true
            }
        })

        binding.searchBar.setOnCloseListener {
            hideSearchView()
            true
        }

        addView(binding.root)
    }

    fun setOnInputListener(listener: (String) -> Unit) {
        onInputListener = listener
    }

    private fun hideSearchView() {
        binding.searchBar.visibility = GONE
        binding.searchButton.visibility = VISIBLE

        binding.root.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
    }

}