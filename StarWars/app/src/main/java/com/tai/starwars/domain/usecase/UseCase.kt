package com.tai.starwars.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.ResourceObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, Params> internal constructor(private val mPostExecutionThread: Scheduler) {

    private val mSubscription = CompositeDisposable()

    /**
     * Builds an [Observable] which will be used when executing the current [UseCase].
     */
    protected abstract fun buildObservable(params: Params?): Observable<T>

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build
     * with [.buildObservable].
     */
    @JvmOverloads
    fun execute(useCaseSubscriber: ResourceObserver<T>, params: Params? = null) {
        mSubscription.add(buildObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(mPostExecutionThread)
                .subscribeWith(useCaseSubscriber))
    }

    /**
     * Unsubscribes from current [io.reactivex.disposables.Disposable].
     */
    fun unsubscribe() {
        if (!mSubscription.isDisposed) {
            mSubscription.dispose()
        }
    }
}
