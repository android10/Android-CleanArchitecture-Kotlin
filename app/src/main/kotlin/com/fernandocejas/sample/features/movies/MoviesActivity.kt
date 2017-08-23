package com.fernandocejas.sample.features.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fernandocejas.sample.BaseActivity

class MoviesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(savedInstanceState, MoviesFragment())
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }
}
