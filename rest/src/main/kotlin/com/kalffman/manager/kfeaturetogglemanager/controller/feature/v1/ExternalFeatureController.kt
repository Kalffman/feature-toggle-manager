package com.kalffman.manager.kfeaturetogglemanager.controller.feature.v1

import com.kalffman.manager.kfeaturetogglemanager.annotation.ValidateIFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.ManageFeature
import com.kalffman.manager.kfeaturetogglemanager.input.dto.IFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.dto.UpdateIFeatureDTO
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping("/feature/v1/external")
@Validated
class ExternalFeatureController(
    private val manageFeature: ManageFeature
) {
    private val logger = LoggerFactory.getLogger(ExternalFeatureController::class.java)


    @GetMapping("/{id}")
    fun getFeatureByExternalId(@PathVariable id: UUID): ResponseEntity<IFeatureDTO> {
        logger.info("c=FeatureController, m=getAllFeature, status=started")

        val response = manageFeature.retrieve(id)

        return if (response != null) ResponseEntity.ok(response)
        else ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun putExternalFeature(
        @PathVariable id: UUID,
        @Valid @ValidateIFeatureDTO
        @RequestBody dto: UpdateIFeatureDTO,
    ): ResponseEntity<Any> {
        logger.info("c=FeatureController, m=putExternalFeature, status=started, id=$id, dto=$dto")

        val response = manageFeature.update(id, dto)

        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deleteExternalFeature(@PathVariable id: UUID): ResponseEntity<Any> {
        logger.info("c=FeatureController, m=deleteFeature, status=started, id=$id")

        manageFeature.delete(id)

        return ResponseEntity.noContent().build()
    }

    private fun solveUri(dto: IFeatureDTO, builder: UriComponentsBuilder): URI =
        builder.path("/v1/feature/{id}").buildAndExpand(dto.id).toUri()
}
