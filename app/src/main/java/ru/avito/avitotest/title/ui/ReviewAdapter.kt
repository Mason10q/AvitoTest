package ru.avito.avitotest.title.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.avito.avitotest.databinding.ItemReviewBinding
import ru.avito.avitotest.databinding.ItemTitleBinding
import ru.avito.avitotest.title.model.entities.Review

class ReviewAdapter: PagingDataAdapter<Review, ReviewAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with (holder.binding) {
            author.text = item?.author ?: ""
            review.text = item?.review ?: ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )



    inner class ViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
                oldItem.review == newItem.review


            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
                oldItem == newItem
        }
    }

}