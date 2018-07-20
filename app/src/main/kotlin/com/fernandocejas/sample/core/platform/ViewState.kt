package com.fernandocejas.sample.core.platform

import com.fernandocejas.sample.core.exception.Failure

sealed class ViewState {
    data class Success(val data: Any) : ViewState() { inline fun <reified T> responseTo() = data as T }
    data class Error(val error: Failure?) : ViewState()
}
