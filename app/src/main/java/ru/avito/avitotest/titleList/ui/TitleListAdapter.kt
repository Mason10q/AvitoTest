package ru.avito.avitotest.titleList.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import com.squareup.picasso.Picasso
import ru.avito.avitotest.R
import ru.avito.avitotest.core.BaseAdapter
import ru.avito.avitotest.databinding.ItemTitleBinding
import ru.avito.avitotest.titleList.model.entities.Title
import java.lang.IllegalArgumentException
import javax.inject.Inject

class TitleListAdapter : BaseAdapter<Title, ItemTitleBinding>(ItemTitleBinding::inflate) {

    @Inject lateinit var picasso: Picasso

    fun restoreItems(items: List<Title>) {
        clearItems()
        addItems(items)
    }

    override fun bindView(binding: ItemTitleBinding, item: Title, context: Context) {
        with(binding) {
            try {
                picasso.load(item.posterPreviewUrl).into(posterImage)
            } catch (e: IllegalArgumentException) {
                posterImage.setImageResource(R.drawable.ic_kinopoisk)
            }
            titleName.text = item.name
            titleRating.text = item.rating.toString()
            titleCountryGenre.text = context.resources.getString(R.string.country_genre, item.country, item.genre)

            if (item.alternativeName != "") {
                titleNameYear.text = context.resources.getString(R.string.name_year, item.alternativeName, item.year)
            } else {
                titleNameYear.text = item.year.toString()
            }
        }
    }


}