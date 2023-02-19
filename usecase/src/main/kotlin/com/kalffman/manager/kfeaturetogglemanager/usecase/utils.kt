package com.kalffman.manager.kfeaturetogglemanager.usecase

import com.kalffman.manager.kfeaturetogglemanager.input.dto.FeatureInputDTO
import com.kalffman.manager.kfeaturetogglemanager.output.dto.FeatureOutputDTO

fun FeatureInputDTO.toOutputDTO(): FeatureOutputDTO = FeatureOutputDTO(
    this.id,
    this.name,
    this.description,
    this.enabled,
    this.externalId
)