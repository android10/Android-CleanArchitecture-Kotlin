package com.fernandocejas.sample.framework.exception

sealed class ErrorEvent {
    class Network: ErrorEvent()
    class ServerDown: ErrorEvent()
    class Unknown(private val throwable: Throwable?): ErrorEvent()
    abstract class FeatureError: ErrorEvent()
}
