package com.kalffman.manager.kfeaturetogglemanager.output.redis.exception

import com.kalffman.manager.kfeaturetogglemanager.output.exception.OutputException

class RedisLayerException(
    message: String? = null,
    throwable: Throwable
) : OutputException(
    "adapter:output:redis",
    message,
    throwable
)
