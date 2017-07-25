package com.fernandocejas.sample.features.movies

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fernandocejas.sample.R
import com.fernandocejas.sample.framework.extension.inflate
import kotlinx.android.synthetic.main.row_movie.view.*
import javax.inject.Inject

class MoviesAdapter @Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    internal var movies: List<MovieViewModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.row_movie))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = viewHolder.bind(movies[position])

    override fun getItemCount() = movies.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieViewModel: MovieViewModel?) {
            with(itemView) {
                title.text = movieViewModel?.title
            }
        }
    }
}
