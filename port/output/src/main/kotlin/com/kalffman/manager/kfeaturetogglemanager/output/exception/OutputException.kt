package com.kalffman.manager.kfeaturetogglemanager.output.exception

open class OutputException(
    layer: String? = null,
    message: String?,
    cause: Throwable? = null
) : RuntimeException("[${layer ?: "output.exception"}]:$message", cause)
