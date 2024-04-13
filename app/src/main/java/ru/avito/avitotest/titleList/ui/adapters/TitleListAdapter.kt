package ru.avito.avitotest.titleList.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.avito.avitotest.R
import ru.avito.avitotest.databinding.ItemTitleBinding
import ru.avito.avitotest.titleList.model.entities.Title
import java.lang.IllegalArgumentException
import javax.inject.Inject

class TitleListAdapter : PagingDataAdapter<Title, TitleListAdapter.ViewHolder>(DIFF_CALLBACK) {

    @Inject lateinit var picasso: Picasso

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            try {
                picasso.load(item?.posterPreviewUrl)
                    .placeholder(R.drawable.ic_kinopoisk)
                    .error(R.drawable.ic_kinopoisk)
                    .into(posterImage)
            } catch (e: IllegalArgumentException) {
                posterImage.setImageResource(R.drawable.ic_kinopoisk)
            }


            titleName.text = item?.name
            titleRating.text = item?.rating.toString()
            titleCountryGenre.text = root.context.getString(R.string.country_genre, item?.country, item?.genre)

            if (item?.alternativeName != "") {
                titleNameYear.text = root.context.getString(R.string.name_year, item?.alternativeName, item?.year)
            } else {
                titleNameYear.text = item.year.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    inner class ViewHolder(val binding: ItemTitleBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Title>() {
            override fun areItemsTheSame(oldItem: Title, newItem: Title): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Title, newItem: Title): Boolean =
                oldItem == newItem
        }
    }
}