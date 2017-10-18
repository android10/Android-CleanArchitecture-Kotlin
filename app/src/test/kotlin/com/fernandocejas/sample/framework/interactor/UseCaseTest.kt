package com.fernandocejas.sample.framework.interactor

import com.fernandocejas.sample.UnitTest
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import org.amshove.kluent.shouldBe
import org.junit.Test

class UseCaseTest : UnitTest() {

    private val rxSingleUseCase = TestRxSingleUseCase()
    private val rxObservableUseCase = TestRxObservableUseCase()
    private val rxFlowableUseCase = TestRxFlowableUseCase()
    private val rxCompletableUseCase = TestUseCaseRxCompletable()

    @Test fun `should dispose disposables in RxSingle UseCase using Observer`() {
        rxSingleUseCase.execute(TestUseCaseRxSingleObserver())
        `assert disposables are disposed`(rxSingleUseCase)
    }

    @Test fun `should dispose disposables in RxSingle UseCase using Functions`() {
        rxSingleUseCase.execute({ _ -> }, { _ -> }, null)
        `assert disposables are disposed`(rxSingleUseCase)
    }

    @Test fun `should dispose disposables in RxObservable UseCase using Observer`() {
        rxObservableUseCase.execute(TestUseCaseRxObservableObserver())
        `assert disposables are disposed`(rxObservableUseCase)
    }

    @Test fun `should dispose disposables in RxObservable UseCase using Functions`() {
        rxObservableUseCase.execute({ _ -> }, { _ -> }, null)
        `assert disposables are disposed`(rxObservableUseCase)
    }

    @Test fun `should dispose disposables in RxFlowable UseCase using Observer`() {
        rxFlowableUseCase.execute(TestUseCaseRxFlowableObserver())
        `assert disposables are disposed`(rxFlowableUseCase)
    }

    @Test fun `should dispose disposables in RxFlowable UseCase using Functions`() {
        rxFlowableUseCase.execute({ _ -> }, { _ -> }, null)
        `assert disposables are disposed`(rxFlowableUseCase)
    }

    @Test fun `should dispose disposables in RxCompletable UseCase with Empty Params`() {
        rxCompletableUseCase.execute(null)
        `assert disposables are disposed`(rxCompletableUseCase)
    }

    @Test fun `should dispose disposables in RxCompletable UseCase using Functions`() {
        rxCompletableUseCase.execute({ }, null)
        `assert disposables are disposed`(rxCompletableUseCase)
    }

    private fun `assert disposables are disposed`(useCase: UseCase<Any, UseCase.None>) {
        useCase.disposables.isDisposed shouldBe false
        useCase.dispose()
        useCase.disposables.isDisposed shouldBe true
    }

    private class TestUseCaseRxSingleObserver : UseCaseObserver.RxSingle<String>()
    private class TestRxSingleUseCase : UseCase.RxSingle<String, UseCase.None>() {
        override fun build(params: None?): Single<String> = Single.just("test")
    }

    private class TestUseCaseRxObservableObserver : UseCaseObserver.RxObservable<String>()
    private class TestRxObservableUseCase : UseCase.RxObservable<String, UseCase.None>() {
        override fun build(params: None?): Observable<String> = Observable.just("test")
    }

    private class TestUseCaseRxFlowableObserver : UseCaseObserver.RxFlowable<String>()
    private class TestRxFlowableUseCase : UseCase.RxFlowable<String, UseCase.None>() {
        override fun build(params: None?): Flowable<String> = Flowable.just("test")
    }

    private class TestUseCaseRxCompletable : UseCase.RxCompletable<UseCase.None>() {
        override fun build(params: None?): Completable  = Completable.complete()
    }
}
