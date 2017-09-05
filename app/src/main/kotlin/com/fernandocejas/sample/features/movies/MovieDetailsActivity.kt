package com.fernandocejas.sample.features.movies

import android.content.Context
import android.content.Intent
import com.fernandocejas.sample.BaseActivity

class MovieDetailsActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_MOVIE_ID = "com.fernandocejas.INTENT_PARAM_MOVIE_ID"

        fun callingIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_MOVIE_ID, movieId)
            return intent
        }
    }

    override fun fragment() = MovieDetailsFragment.forMovie(intent.getIntExtra(INTENT_EXTRA_PARAM_MOVIE_ID, -1))
}
