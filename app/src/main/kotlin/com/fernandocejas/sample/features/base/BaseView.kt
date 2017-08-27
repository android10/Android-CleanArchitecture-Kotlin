package com.fernandocejas.sample.features.base

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(e: Throwable)
    fun dispose()
}
