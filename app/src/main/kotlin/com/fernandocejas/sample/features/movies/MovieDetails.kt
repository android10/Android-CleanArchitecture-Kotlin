package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.extension.empty

class MovieDetails
private constructor(val id: Int,
                    val title: String,
                    val poster: String,
                    val summary: String,
                    val cast: String,
                    val director: String,
                    val year: Int,
                    val trailer: String) {

    private constructor(builder: Builder) : this(builder.id, builder.title, builder.poster,
            builder.summary, builder.cast, builder.director, builder.year, builder.trailer)

    companion object { fun create(init: Builder.() -> Unit) = Builder(init).build() }

    class Builder private constructor() {
        constructor(init: Builder.() -> Unit) : this() { init() }

        var id = 0
        var title = String.empty()
        var poster = String.empty()
        var summary = String.empty()
        var cast = String.empty()
        var director = String.empty()
        var year = 0
        var trailer = String.empty()

        fun build() = MovieDetails(this)
    }
}
