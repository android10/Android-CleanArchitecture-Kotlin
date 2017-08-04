package com.fernandocejas.sample.framework.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton class ThreadScheduler
@Inject constructor() : ExecutionScheduler {
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun highPriority(): Scheduler = Schedulers.newThread() //TODO: use thread pool
    override fun lowPriority(): Scheduler = Schedulers.newThread()  //TODO: use thread pool
}
