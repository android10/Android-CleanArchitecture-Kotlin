package com.fernandocejas.sample.features.movies

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

class MoviesAdapter @Inject constructor(context: Context) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val layoutInflater: LayoutInflater
    var movies: List<MovieViewModel>

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        movies = emptyList()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun getItemCount() = movies.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
