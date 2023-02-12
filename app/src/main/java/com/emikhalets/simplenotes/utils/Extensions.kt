package com.emikhalets.simplenotes.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

fun <T> executeSync(block: () -> T): Result<T> {
    return runCatching { block() }.onFailure { it.printStackTrace() }
}

suspend fun <T> executeAsync(block: suspend () -> T): Result<T> {
    return runCatching { block() }.onFailure { it.printStackTrace() }
}

suspend fun <T> executeAsync(dispatcher: CoroutineDispatcher, block: suspend () -> T): Result<T> {
    return withContext(dispatcher) {
        runCatching { block() }.onFailure { it.printStackTrace() }
    }
}