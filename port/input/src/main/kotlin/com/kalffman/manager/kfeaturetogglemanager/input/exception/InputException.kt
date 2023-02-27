package com.kalffman.manager.kfeaturetogglemanager.input.exception

open class InputException(
    layer: String? = null,
    message: String?,
    cause: Throwable
) : RuntimeException("[$layer]:$message", cause)