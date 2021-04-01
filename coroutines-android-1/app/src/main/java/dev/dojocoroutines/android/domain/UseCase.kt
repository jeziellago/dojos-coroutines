package dev.dojocoroutines.android.domain

import io.reactivex.Single

interface UseCase<T: Any> {
    fun execute(): Single<T>
}