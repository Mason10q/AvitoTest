package com.example.abstracttesting.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.avito.avitotest.core.AdapterCallbacks

abstract class BaseAdapter<DATA : Any, B : ViewBinding>(
    private val inflater: (LayoutInflater, ViewGroup, Boolean) -> B
) : RecyclerView.Adapter<BaseAdapter.ViewHolder<DATA, B>>() {

    private val items = mutableListOf<DATA>()

    abstract fun bindView(binding: B, item: DATA, context: Context)

    private val callbacks = object : AdapterCallbacks<DATA, B> {
        override fun bindViews(binding: B, item: DATA, context: Context) =
            bindView(binding, item, context)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<DATA>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: DATA) {
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addItems(items: List<DATA>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder<DATA, B> =
        ViewHolder(
            inflater(LayoutInflater.from(parent.context), parent, false), callbacks, parent.context
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder<DATA, B>, pos: Int) =
        holder.bind(items[pos], pos)

    class ViewHolder<DATA : Any, B : ViewBinding>(
        private val binding: B,
        private val callbacks: AdapterCallbacks<DATA, B>,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DATA, position: Int) {
            callbacks.bindViews(binding, item, context)
            binding.root.setOnClickListener { callbacks.onViewClicked(binding.root, item) }
        }

    }
}