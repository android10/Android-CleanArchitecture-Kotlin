/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.sample.features.movies.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fernandocejas.sample.core.extension.loadFromUrl
import com.fernandocejas.sample.core.navigation.Navigator
import com.fernandocejas.sample.databinding.RowMovieBinding
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    internal var collection: List<MovieView> by Delegates.observable(emptyList()) { _, _, _ ->
        //TODO: go for a more efficient solution
        notifyDataSetChanged()
    }

    internal var clickListener: (MovieView, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowMovieBinding = RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(rowMovieBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(collection[position], clickListener)
    }

    override fun getItemCount() = collection.size

    class ViewHolder(private val rowMovieBinding: RowMovieBinding) : RecyclerView.ViewHolder(rowMovieBinding.root) {
        fun bind(movieView: MovieView, clickListener: (MovieView, Navigator.Extras) -> Unit) {
            rowMovieBinding.moviePoster.loadFromUrl(movieView.poster)
            itemView.setOnClickListener {
                clickListener(
                    movieView,
                    Navigator.Extras(rowMovieBinding.moviePoster)
                )
            }
        }
    }
}
