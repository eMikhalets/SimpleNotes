package com.emikhalets.simplenotes.utils

import android.content.Context
import android.widget.Toast
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

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}