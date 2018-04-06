package com.fernandocejas.sample.framework.exception

sealed class Failure {
    class Network: Failure()
    class ServerDown: Failure()
    class Unknown(private val throwable: Throwable?): Failure()
    abstract class FeatureFailure: Failure()
}
