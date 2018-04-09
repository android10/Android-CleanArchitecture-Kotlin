package com.fernandocejas.sample.framework.platform

import android.content.Context
import com.fernandocejas.sample.framework.extension.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) {
    val isConnected get() =
        context.networkInfo != null && context.networkInfo.isConnectedOrConnecting
}