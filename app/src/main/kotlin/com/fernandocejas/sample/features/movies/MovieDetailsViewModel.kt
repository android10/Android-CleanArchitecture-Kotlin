package com.fernandocejas.sample.features.movies

data class MovieDetailsViewModel (val id: Int,
                                  val title: String,
                                  val poster: String,
                                  val summary: String,
                                  val cast: String,
                                  val director: String,
                                  val year: Int,
                                  val trailer: String)
