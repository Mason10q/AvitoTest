package ru.avito.avitotest.titleList.ui

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.avito.avitotest.titleList.model.entities.Title
import ru.avito.avitotest.R
import ru.avito.avitotest.databinding.ItemTitleBinding

class TitleListAdapter(private val resources: Resources) : RecyclerView.Adapter<TitleListAdapter.ViewHolder>() {

    private val titles = ArrayList<Title>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = titles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(titles[position])

    fun addItems(items: List<Title>) {
        titles.addAll(titles)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Title) {
            with(binding) {
                titleName.text = item.name
                titleRating.text = item.rating.toString()
                titleNameYear.text = resources.getString(R.string.name_year, item.alternativeName, item.year)
                titleCountryGenre.text = resources.getString(R.string.country_genre, item.country, item.genre)
            }
        }
    }

}