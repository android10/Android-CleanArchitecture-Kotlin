package com.fernandocejas.sample.framework.interactor

import com.fernandocejas.sample.UnitTest
import io.reactivex.Observable
import org.amshove.kluent.shouldBe
import org.junit.Test

class UseCaseTest : UnitTest() {

    @Test
    fun shouldDisposeUseCase() {
        val testUseCase = TestUseCase()

        testUseCase.execute(TestUseCaseObserver())
        testUseCase.disposables.isDisposed shouldBe false

        testUseCase.dispose()
        testUseCase.disposables.isDisposed shouldBe true
    }

    private class TestUseCaseObserver : UseCaseObserver<String>()
    private class TestUseCase : UseCase<String, UseCase.None>() {
        override fun buildObservable(params: None?): Observable<String> = Observable.just("test")
    }
}
