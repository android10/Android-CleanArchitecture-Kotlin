package com.fernandocejas.sample.features.movies

import android.content.Context
import com.fernandocejas.sample.features.movies.PlayMovie.Params
import com.fernandocejas.sample.framework.exception.Failure
import com.fernandocejas.sample.framework.functional.Either
import com.fernandocejas.sample.framework.functional.Either.Right
import com.fernandocejas.sample.framework.interactor.UseCase
import com.fernandocejas.sample.framework.interactor.UseCase.None
import com.fernandocejas.sample.navigation.Navigator
import javax.inject.Inject

class PlayMovie
@Inject constructor(private val context: Context,
                    private val navigator: Navigator) : UseCase<None, Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        navigator.openVideo(context, params.url)
        return Right(None())
    }

    data class Params(val url: String)
}
