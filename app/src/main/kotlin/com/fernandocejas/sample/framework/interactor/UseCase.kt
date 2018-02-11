package com.fernandocejas.sample.framework.interactor

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type

    fun execute(onSuccess: (Type) -> Unit, params: Params) {
        val result = async(CommonPool) { run(params) }
        launch(UI) { onSuccess.invoke(result.await()) }
    }

    class None
}
