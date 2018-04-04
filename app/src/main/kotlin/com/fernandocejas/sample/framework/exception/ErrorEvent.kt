package com.fernandocejas.sample.framework.exception

sealed class ErrorEvent {
    class Network: ErrorEvent()
    class ServerDown: ErrorEvent()
    abstract class FeatureError: ErrorEvent()
}
