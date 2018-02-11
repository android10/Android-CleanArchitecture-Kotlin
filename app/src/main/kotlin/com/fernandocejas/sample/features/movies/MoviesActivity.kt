package com.fernandocejas.sample.features.movies

import android.content.Context
import android.content.Intent
import com.fernandocejas.sample.BaseActivity

class MoviesActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }

    override fun fragment() = MoviesFragment()
}
