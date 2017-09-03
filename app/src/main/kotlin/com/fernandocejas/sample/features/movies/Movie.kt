package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.extension.empty

class Movie
private constructor(val id: Int, val poster: String) {

    private constructor(builder: Builder) : this(builder.id, builder.poster)

    companion object { fun create(init: Builder.() -> Unit) = Builder(init).build() }

    class Builder private constructor() {
        constructor(init: Builder.() -> Unit) : this() { init() }

        var id = 0
        var poster = String.empty()

        fun build() = Movie(this)
    }
}
