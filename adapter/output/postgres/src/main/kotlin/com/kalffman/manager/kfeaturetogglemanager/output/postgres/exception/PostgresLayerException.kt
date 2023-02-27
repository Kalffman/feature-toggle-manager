package com.kalffman.manager.kfeaturetogglemanager.output.postgres.exception

import com.kalffman.manager.kfeaturetogglemanager.output.exception.OutputException

class PostgresLayerException(
    message: String? = null,
    throwable: Throwable
) : OutputException(
    "adapter:output:postgres",
    message,
    throwable
)
