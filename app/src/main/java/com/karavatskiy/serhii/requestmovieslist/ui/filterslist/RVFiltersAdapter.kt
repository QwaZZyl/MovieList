package com.karavatskiy.serhii.requestmovieslist.ui.filterslist

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import com.karavatskiy.serhii.requestmovieslist.R.layout
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterDirector
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterGenre
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterItem
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterYear
import com.karavatskiy.serhii.requestmovieslist.data.model.FiltersData
import com.karavatskiy.serhii.requestmovieslist.data.model.Title
import java.util.SortedSet

/**
 * Created by Serhii on 31.03.2019.
 */
class RVFiltersAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val filters: MutableList<FilterItem> = mutableListOf()
    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return when (type) {
            ItemType.TITLE.value -> FiltersTitleViewHolder(
                inflater.inflate(
                    layout.filter_list_title,
                    viewGroup,
                    false
                )
            )
            else -> FiltersViewHolder(inflater.inflate(layout.filter_list_item, viewGroup, false))
        }
    }

    fun setFilters(filtersData: FiltersData) {
        filters.clear()
        filters.add(filtersData.years.keys.first())
        filters.add(FilterYear(FILTER_ALL))
        filters.addAll(filtersData.years.getValue(filtersData.years.keys.first()))
        filters.add(filtersData.genres.keys.first())
        filters.add(FilterGenre(FILTER_ALL))
        filters.addAll(filtersData.genres.getValue(filtersData.genres.keys.first()))
        filters.add(filtersData.directors.keys.first())
        filters.add(FilterDirector(FILTER_ALL))
        filters.addAll(filtersData.directors.getValue(filtersData.directors.keys.first()))
        notifyDataSetChanged()
    }

    fun getSelectedFilters(filtersData: FiltersData): FiltersData {
        var genreSet: SortedSet<FilterGenre> = sortedSetOf()
        var yearSet: SortedSet<FilterYear> = sortedSetOf()
        var directorSet: SortedSet<FilterDirector> = sortedSetOf()
        for (filter in filters) {
            when (filter) {
                is FilterYear -> {
                    if (filter.isSelected() && filter.criteria == FILTER_ALL) {
                        yearSet = filtersData.years.getValue(filtersData.years.keys.first())
                            .apply { remove(FilterYear(FILTER_ALL)) }
                    }
                    if (filter.isSelected()) {
                        yearSet.add(filter)
                    }
                }
                is FilterGenre -> {
                    if (filter.isSelected() && filter.criteria == FILTER_ALL) {
                        genreSet = filtersData.genres.getValue(filtersData.genres.keys.first())
                    }
                    if (filter.isSelected()) {
                        genreSet.add(filter)
                    }
                }
                is FilterDirector -> {
                    if (filter.isSelected() && filter.criteria == FILTER_ALL) {
                        directorSet = filtersData.directors.getValue(filtersData.directors.keys.first())
                    }
                    if (filter.isSelected()) {
                        directorSet.add(filter)
                    }
                }
            }
        }
        return FiltersData(
            mapOf(Title("Genres") to genreSet),
            mapOf(Title("Years") to yearSet),
            mapOf(Title("Directors") to directorSet)
        )
    }

    fun disableCheckBox() {
        filters.forEach {
            when (it) {
                is FilterYear -> it.setSelected(false)
                is FilterGenre -> it.setSelected(false)
                is FilterDirector -> it.setSelected(false)
            }
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ItemType.TITLE.value -> {
                val filtersTitleViewHolder: FiltersTitleViewHolder = holder as FiltersTitleViewHolder
                val item = filters[position] as Title
                filtersTitleViewHolder.bind(item)
            }
            ItemType.FILTER_YEAR.value -> {
                val filtersViewHolder: FiltersViewHolder = holder as FiltersViewHolder
                val item = filters[position] as FilterYear
                filtersViewHolder.bind(item)
            }
            ItemType.FILTER_GENRE.value -> {
                val filtersViewHolder: FiltersViewHolder = holder as FiltersViewHolder
                val item = filters[position] as FilterGenre
                filtersViewHolder.bind(item)
            }
            ItemType.FILTER_DIRECTOR.value -> {
                val filtersViewHolder: FiltersViewHolder = holder as FiltersViewHolder
                val item = filters[position] as FilterDirector
                filtersViewHolder.bind(item)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (filters[position]) {
            is Title -> ItemType.TITLE.value
            is FilterYear -> ItemType.FILTER_YEAR.value
            is FilterGenre -> ItemType.FILTER_GENRE.value
            is FilterDirector -> ItemType.FILTER_DIRECTOR.value
            else -> ItemType.ERROR.value
        }
    }

    override fun getItemCount(): Int {
        return filters.size
    }

    enum class ItemType(val value: Int) {
        TITLE(0),
        FILTER_YEAR(1),
        FILTER_GENRE(2),
        FILTER_DIRECTOR(3),
        ERROR(-1)
    }

    companion object {
        private const val FILTER_ALL = "All"
    }
}