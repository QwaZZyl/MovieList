package com.karavatskiy.serhii.requestmovieslist.ui.movieslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.karavatskiy.serhii.requestmovieslist.R
import com.karavatskiy.serhii.requestmovieslist.data.model.MovieDescription

/**
 * Created by Serhii on 29.03.2019.
 */
class RVMoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

    private val movies: MutableList<MovieDescription> = mutableListOf()

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setMovies(movies: List<MovieDescription>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return MoviesViewHolder(inflater.inflate(R.layout.movies_list_item, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
