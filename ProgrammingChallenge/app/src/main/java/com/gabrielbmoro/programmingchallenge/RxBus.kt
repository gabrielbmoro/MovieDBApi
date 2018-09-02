package com.gabrielbmoro.programmingchallenge

import rx.Observable
import rx.subjects.PublishSubject

object RxBus {

    private var mpsConnectionSubject : PublishSubject<Boolean> = PublishSubject.create()

    fun setConnectionBoolean(abIsNetwork : Boolean) {
        mpsConnectionSubject.onNext(abIsNetwork)
    }

    fun getConnectionEvent() : Observable<Boolean> {
        return mpsConnectionSubject
    }


}