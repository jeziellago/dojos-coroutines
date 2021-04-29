package com.coroutines.dojo6.rxrefactoring

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

fun observableExample(
    disposables: CompositeDisposable,
    result: (String, String, Any?) -> Unit
) {
    Observable.fromCallable { getUser() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .repeat(5)
        .map { User(it.name) }
        .subscribe({
            result("Observable", "success", it)
        }, {
            result("Observable", "failure", it)
        }).apply { disposables.add(this) }
}
