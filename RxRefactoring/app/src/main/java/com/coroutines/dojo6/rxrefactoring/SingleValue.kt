package com.coroutines.dojo6.rxrefactoring

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

fun singleExample(
    disposables: CompositeDisposable,
    result: (String, String, Any?) -> Unit
) {
    Single.fromCallable { getUser() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { User(it.name) }
        .subscribe({ item ->
            result("Single", "success", item)
        }, { error ->
            result("Single", "failure", error)
        }).apply { disposables.add(this) }
}