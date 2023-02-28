package com.kalffman.manager.kfeaturetogglemanager.input.exception

open class InputException(
    layer: String? = null,
    message: String?,
    cause: Throwable? = null
) : RuntimeException("[${layer ?: "input.exception"}]:$message", cause)
