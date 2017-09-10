package com.fernandocejas.sample.features.movies

import android.os.Parcel
import com.fernandocejas.sample.framework.platform.KParcelable
import com.fernandocejas.sample.framework.platform.parcelableCreator

data class MovieViewModel(val id: Int, val poster: String) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::MovieViewModel)
    }

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(poster)
        }
    }
}
