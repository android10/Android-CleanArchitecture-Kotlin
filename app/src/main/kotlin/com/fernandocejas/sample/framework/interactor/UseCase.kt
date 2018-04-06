package com.fernandocejas.sample.framework.interactor

import com.fernandocejas.sample.framework.exception.Failure
import com.fernandocejas.sample.framework.exception.Failure.Unknown
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type

    fun execute(onSuccess: (Type) -> Unit, params: Params, onError: (Failure) -> Unit = {}) {
        val job = async(CommonPool) { run(params) }
        launch(UI) {
            val result = job.await()
            when (job.isCompletedExceptionally) {
                true -> onError.invoke(Unknown(job.getCompletionExceptionOrNull()))
                false -> onSuccess.invoke(result)
            }
        }
    }

    class None
}
