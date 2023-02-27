package com.kalffman.manager.kfeaturetogglemanager.output.exception

open class OutputException(
    layer: String? = null,
    message: String?,
    cause: Throwable
) : RuntimeException("[$layer]:$message", cause)
