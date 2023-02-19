package com.kalffman.manager.kfeaturetogglemanager.output

interface PropagateChange<T> {
    fun propagate(entity: T)
}