package com.karavatskiy.serhii.requestmovieslist.ui.filterslist

import android.support.v7.widget.RecyclerView
import android.view.View
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterDirector
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterGenre
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterYear
import com.karavatskiy.serhii.requestmovieslist.data.model.Title
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.filter_list_item.itemCheckBox
import kotlinx.android.synthetic.main.filter_list_item.itemText
import kotlinx.android.synthetic.main.filter_list_title.itemTitle

/**
 * Created by Serhii on 31.03.2019.
 */
class FiltersViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(filter: FilterYear) {
        itemText.text = filter.criteria
        itemCheckBox.isChecked = filter.isSelected()
        itemCheckBox.setOnCheckedChangeListener { _, isChecked -> filter.setSelected(isChecked) }
    }

    fun bind(filter: FilterGenre) {
        itemText.text = filter.criteria
        itemCheckBox.isChecked = filter.isSelected()
        itemCheckBox.setOnCheckedChangeListener { _, isChecked -> filter.setSelected(isChecked) }
    }

    fun bind(filter: FilterDirector) {
        itemText.text = filter.criteria
        itemCheckBox.isChecked = filter.isSelected()
        itemCheckBox.setOnCheckedChangeListener { _, isChecked -> filter.setSelected(isChecked) }
    }
}

class FiltersTitleViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(title: Title) {
        itemTitle.text = title.body
    }
}