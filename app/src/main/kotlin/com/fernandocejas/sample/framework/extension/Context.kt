package com.fernandocejas.sample.framework.extension

import android.content.Context
import android.net.ConnectivityManager

val Context.networkInfo get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

