package com.coroutines.dojo6.rxrefactoring

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


fun completableExample(
    disposables: CompositeDisposable,
    result: (String, String, Any?) -> Unit
) {
    Completable.fromCallable { getUser() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            result("Completable", "success", null)
        }, {
            result("Completable", "failure", null)
        }).apply { disposables.add(this) }
}