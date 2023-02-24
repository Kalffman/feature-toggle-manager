package com.kalffman.manager.kfeaturetogglemanager.controller.feature.v1

import com.kalffman.manager.kfeaturetogglemanager.input.ManageFeature
import com.kalffman.manager.kfeaturetogglemanager.input.dto.IFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.dto.NewIFeatureDTO
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/feature/v1")
class FeatureController(
    private val manageFeature: ManageFeature
) {
    private val logger = LoggerFactory.getLogger(FeatureController::class.java)

    @PostMapping
    fun postFeature(@RequestBody dto: NewIFeatureDTO, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<Any> {
        logger.info("c=FeatureController, m=postFeature, status=started, dto=$dto")

        val response = manageFeature.create(dto)

        return ResponseEntity
            .created(solveUri(response, uriComponentsBuilder))
            .body(response)
    }

    @GetMapping
    fun getAllFeature(): ResponseEntity<Any> {
        logger.info("c=FeatureController, m=getAllFeature, status=started")

        val response = manageFeature.retrieve()

        return if (response.isNotEmpty())
            ResponseEntity.ok(response)
        else
            ResponseEntity.noContent().build()
    }

    private fun solveUri(dto: IFeatureDTO, builder: UriComponentsBuilder): URI =
        builder.path("/v1/feature/{id}").buildAndExpand(dto.id).toUri()
}
