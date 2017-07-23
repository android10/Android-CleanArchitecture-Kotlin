package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.extension.empty

class Movie private constructor(val id: Int,
                                val title: String,
                                val year: Int,
                                val poster: String,
                                val color: String) {

    private constructor(builder: Builder) :
            this(builder.id, builder.title, builder.year, builder.poster, builder.color)

    companion object {
        fun create(init: Builder.() -> Unit) = Builder(init).build()
    }

    class Builder private constructor() {

        constructor(init: Builder.() -> Unit) : this() {
            init()
        }

        var id: Int = 0
        var title: String = String.empty()
        var year: Int = 0
        var poster: String = String.empty()
        var color: String = String.empty()

        fun build() = Movie(this)
    }
}
