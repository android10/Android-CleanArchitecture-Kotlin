package com.fernandocejas.sample.framework.interactor

import com.fernandocejas.sample.UnitTest
import io.reactivex.Observable
import org.amshove.kluent.shouldBe
import org.junit.Test

class UseCaseTest : UnitTest() {

    @Test fun shouldDisposeUseCase() {
        val testUseCase = TestUseCase()

        testUseCase.execute(TestUseCaseObserver())
        testUseCase.isNotDisposed()

        testUseCase.dispose()
        testUseCase.isDisposed()
    }

    private class TestUseCaseObserver : UseCaseObserver.RxObservable<String>()
    private class TestUseCase : UseCase.RxObservable<String, UseCase.None>() {
        override fun build(params: None?): Observable<String> = Observable.just("test")
        fun isDisposed() = disposables.isDisposed shouldBe true
        fun isNotDisposed() = disposables.isDisposed shouldBe false
    }
}
