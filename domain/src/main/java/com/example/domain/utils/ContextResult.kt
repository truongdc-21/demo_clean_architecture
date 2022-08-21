package com.example.domain.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class ContextResult {

    protected suspend fun <R> withContextResult(
        dispatcherContextIO: CoroutineContext = Dispatchers.IO,
        requestBlock: suspend CoroutineScope.() -> R
    ): DataResult<R> = withContext(dispatcherContextIO) {
        return@withContext try {
            val response = requestBlock()
            DataResult.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            DataResult.Error(e)
        }
    }

}
