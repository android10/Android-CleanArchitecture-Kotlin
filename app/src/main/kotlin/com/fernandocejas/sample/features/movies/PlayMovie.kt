package com.fernandocejas.sample.features.movies

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.fernandocejas.sample.features.movies.PlayMovie.Params
import com.fernandocejas.sample.framework.interactor.UseCase
import io.reactivex.Completable
import javax.inject.Inject


class PlayMovie
@Inject constructor() : UseCase.RxCompletable<Params>() {

    override fun build(params: Params?): Completable =
        Completable.fromAction { watchVideoFromUrl(params!!.context, params.url) }

    private fun watchVideoFromUrl(context: Context, videoUrl: String) =
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)))

    class Params(val context: Context, val url: String)
}
