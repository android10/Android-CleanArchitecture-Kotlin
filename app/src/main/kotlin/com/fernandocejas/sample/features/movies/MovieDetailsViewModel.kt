package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.extension.empty

data class MovieDetailsViewModel(val id: Int, val poster: String = String.empty())
