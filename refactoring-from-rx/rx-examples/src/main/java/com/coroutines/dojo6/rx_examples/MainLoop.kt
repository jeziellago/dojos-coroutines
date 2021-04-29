
fun keepRunning(block: () -> Unit) {
    block()
    while (true) Thread.sleep(1)
}

fun printThread() {
    println("Thread: ${Thread.currentThread().name}")
}