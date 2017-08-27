package com.fernandocejas.sample.features.base

abstract class Presenter<View> {

    /**
     * Returns a current view attached to the presenter.
     *
     * @return a current attached view.
     */
    abstract var view: View

    /**
     * This method is being called when a view gets attached to it.
     *
     * This method is intended for overriding.
     *
     * @param view a view that should be taken.
     */
    protected open fun onTakeView(view: View) {
    }

    /**
     * This method is being called when a view gets detached from the presenter.
     *
     * This method is intended for overriding.
     */
    protected open fun onDropView() {
    }

    /**
     * Attaches a view to the presenter.
     *
     * @param view a view to attach.
     */
    open fun takeView(view: View) {
        this.view = view
        onTakeView(view)
    }

    /**
     * Detaches the presenter from a view.
     */
    open fun dropView() {
        onDropView()
        disposeView()
    }

    /**
     * Dispose a view.
     */
    abstract fun disposeView()
}
