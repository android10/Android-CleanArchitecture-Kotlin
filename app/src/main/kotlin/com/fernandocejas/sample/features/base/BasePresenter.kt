package com.fernandocejas.sample.features.base

abstract class BasePresenter<V : BaseView> : Presenter<V>() {

    override fun disposeView() = view.dispose()

    override fun dropView() = super.dropView()

    override fun takeView(view: V) = super.takeView(view)

    fun initialize(view: V) {
        takeView(view)
        view.hideLoading()
    }

    fun finalize() = dropView()

    open fun onComplete() = onCompleteBase()
    open fun onNext(response: Any) = onNextBase(response)
    open fun onError(e: Throwable) = onErrorBase(e)

    private fun onCompleteBase() = view.hideLoading()
    private fun onNextBase(response: Any) = view.hideLoading()
    private fun onErrorBase(e: Throwable) = view.apply {
        hideLoading()
        showError(e)
    }
}
