package com.fernandocejas.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fernandocejas.sample.di.ApplicationComponent
import com.fernandocejas.sample.features.base.BaseView

abstract class BaseFragment : Fragment(), BaseView {
    init {
        retainInstance = true
    }

    abstract fun layoutId(): Int

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity.application as AndroidApplication).appComponent
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater?.inflate(layoutId(), container, false)

    override fun showLoading() {
        //TODO: implement method
    }

    override fun hideLoading() {
        //TODO: implement method
    }

    override fun showError(e: Throwable) {
        TODO("not implemented")
    }

    override fun dispose() {
        //TODO: implement method
    }
}
