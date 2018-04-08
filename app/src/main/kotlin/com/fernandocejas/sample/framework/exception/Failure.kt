package com.fernandocejas.sample.framework.exception

sealed class Failure {
    class NetworkConnection: Failure()
    class ServerError: Failure()
    abstract class FeatureFailure: Failure()
}
