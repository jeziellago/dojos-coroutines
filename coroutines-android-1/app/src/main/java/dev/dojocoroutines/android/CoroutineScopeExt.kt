package dev.dojocoroutines.android

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.launchSafe(
    onError: ((Throwable) -> Unit)? = null,
    action: suspend () -> Unit,
): Job {
    return launch {
        runCatching { action() }.onFailure { onError?.invoke(it) }
    }
}