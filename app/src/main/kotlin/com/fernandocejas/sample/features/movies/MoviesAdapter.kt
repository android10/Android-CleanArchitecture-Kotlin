package com.fernandocejas.sample.features.movies

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fernandocejas.sample.R
import com.fernandocejas.sample.framework.extension.inflate
import com.fernandocejas.sample.framework.extension.loadFromUrl
import com.fernandocejas.sample.navigation.Navigator
import kotlinx.android.synthetic.main.row_movie.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class MoviesAdapter
@Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    internal var collection: List<MovieModel> by Delegates.observable(emptyList()) {
        _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (MovieModel, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.row_movie))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieModel: MovieModel, clickListener: (MovieModel, Navigator.Extras) -> Unit) {
            itemView.moviePoster.loadFromUrl(movieModel.poster)
            itemView.setOnClickListener { clickListener(movieModel, Navigator.Extras(itemView.moviePoster)) }
        }
    }
}
