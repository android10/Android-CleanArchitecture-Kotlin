package com.fernandocejas.sample.features.movies

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.fernandocejas.sample.features.movies.PlayMovie.Params
import com.fernandocejas.sample.framework.extension.empty
import com.fernandocejas.sample.framework.interactor.UseCase
import io.reactivex.Completable
import javax.inject.Inject


class PlayMovie
@Inject constructor() : UseCase.RxCompletable<Params>() {

    private val VIDEO_URL_HTTP = "http://www.youtube.com/watch?v="
    private val VIDEO_URL_HTTPS = "https://www.youtube.com/watch?v="

    override fun build(params: Params?): Completable =
        Completable.fromAction { watchVideoFromUrl(params!!.context, params.url) }

    private fun watchVideoFromUrl(context: Context, videoUrl: String) {
        try {
            context.startActivity(createYoutubeIntent(videoUrl))
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)))
        }
    }

    private fun createYoutubeIntent(videoUrl: String): Intent {
        val videoId = when {
            videoUrl.startsWith(VIDEO_URL_HTTP) -> videoUrl.replace(VIDEO_URL_HTTP, String.empty())
            videoUrl.startsWith(VIDEO_URL_HTTPS) -> videoUrl.replace(VIDEO_URL_HTTPS, String.empty())
            else -> videoUrl
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
        intent.putExtra("force_fullscreen", true)

        return intent
    }

    data class Params(val context: Context, val url: String)
}
