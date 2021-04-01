package dev.dojocoroutines.android.domain

import android.os.SystemClock
import io.reactivex.Single

class GetFactUseCase : UseCase<Fact> {
    
    override fun execute(): Single<Fact> {
        return Single.just(SystemClock.sleep(500L))
            .map { Fact("Hello Fact!!!") }
    }
}