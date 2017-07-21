package com.fernandocejas.sample.extension

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)
fun ImageView.loadFromUrl(url: String): Unit = throw NotImplementedError("not implemented")
