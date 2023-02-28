package com.kalffman.manager.kfeaturetogglemanager.usecase

import com.kalffman.manager.kfeaturetogglemanager.input.dto.IFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.dto.NewIFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO

fun NewIFeatureDTO.toOutputDTO(): OFeatureDTO = OFeatureDTO(
    name = this.name,
    description = this.description,
    enabled = this.enabled,
    validAfter = this.validAfter,
    validBefore = this.validBefore,
    rules = this.rules?.map { it.toOutputDTO() }
)

fun NewIFeatureDTO.Rule.toOutputDTO(): OFeatureDTO.Rule = OFeatureDTO.Rule(
    this.id,
    this.field,
    this.ruleType,
    this.referenceValue,
    this.operationType,
    this.composition?.toOutputDTO(),
    this.compositionType
)

fun OFeatureDTO.toInputDTO(): IFeatureDTO = IFeatureDTO(
    id,
    externalId,
    name,
    description,
    enabled,
    validAfter,
    validBefore,
    created,
    lastUpdate,
    rules?.map { it.toInputDTO() }
)

fun OFeatureDTO.Rule.toInputDTO(): IFeatureDTO.Rule = IFeatureDTO.Rule(
    this.id,
    this.field,
    this.ruleType,
    this.referenceValue,
    this.operationType,
    this.composition?.toInputDTO(),
    this.compositionType
)

fun IFeatureDTO.toOutputDTO(): OFeatureDTO = OFeatureDTO(
    this.id,
    this.externalId,
    this.name,
    this.description,
    this.enabled,
    this.validAfter,
    this.validBefore,
    this.created,
    this.lastUpdate,
    this.rules?.map { it.toOutputDTO() }
)

fun IFeatureDTO.Rule.toOutputDTO(): OFeatureDTO.Rule = OFeatureDTO.Rule(
    this.id,
    this.field,
    this.ruleType,
    this.referenceValue,
    this.operationType,
    this.composition?.toOutputDTO(),
    this.compositionType
)
