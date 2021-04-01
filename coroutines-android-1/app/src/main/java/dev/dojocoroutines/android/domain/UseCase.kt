package dev.dojocoroutines.android.domain

interface UseCase<T: Any> {
    suspend fun execute(): T
}