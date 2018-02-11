package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.extension.empty

data class Movie(val id: Int, val poster: String) {

    companion object {
        fun empty() = Movie(0, String.empty())
    }
}
