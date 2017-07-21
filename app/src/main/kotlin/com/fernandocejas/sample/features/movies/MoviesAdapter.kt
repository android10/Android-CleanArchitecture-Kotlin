package com.fernandocejas.sample.features.movies

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

class MoviesAdapter @Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    var movies: List<MovieViewModel> = emptyList()

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun getItemCount() = movies.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
