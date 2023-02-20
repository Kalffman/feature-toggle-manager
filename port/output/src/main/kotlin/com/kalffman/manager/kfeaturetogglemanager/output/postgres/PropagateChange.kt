package com.kalffman.manager.kfeaturetogglemanager.output.postgres

interface PropagateChange<T> {
    fun propagate(entity: T)
}
