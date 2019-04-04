package com.karavatskiy.serhii.requestmovieslist.ui.movieslist

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.karavatskiy.serhii.requestmovieslist.R
import com.karavatskiy.serhii.requestmovieslist.data.model.MovieDescription
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movies_list_item.icMoviePoster
import kotlinx.android.synthetic.main.movies_list_item.tvDescription
import kotlinx.android.synthetic.main.movies_list_item.tvDirector
import kotlinx.android.synthetic.main.movies_list_item.tvGenre
import kotlinx.android.synthetic.main.movies_list_item.tvTitle
import kotlinx.android.synthetic.main.movies_list_item.tvYear

class MoviesViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(movieDescription: MovieDescription) {
        tvTitle.text = movieDescription.title
        tvYear.text = movieDescription.year.toString()
        tvDescription.text = movieDescription.description
        tvDirector.text = movieDescription.director
        tvGenre.text = movieDescription.genre.joinToString()

        Glide.with(containerView)
            .asBitmap()
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .load(movieDescription.image)
            .into(icMoviePoster)
    }
}