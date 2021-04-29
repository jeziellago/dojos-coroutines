import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

fun hardOperation(): String {
    Thread.sleep(1000L)
    return "Operation completed!"
}

fun completableRx() {
    printThread()
    Completable.fromCallable { hardOperation() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .blockingSubscribe({
            printThread()
            println("Completable success!")
        },{
            println("Completable error: $it")
        })
}

fun main() = keepRunning {
    completableRx()
}