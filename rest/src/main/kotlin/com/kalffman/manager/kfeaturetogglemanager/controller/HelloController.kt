package com.kalffman.manager.kfeaturetogglemanager.controller

import com.kalffman.manager.kfeaturetogglemanager.input.ManageFeature
import com.kalffman.manager.kfeaturetogglemanager.input.dto.FeatureInputDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val manageFeature: ManageFeature
) {


    @GetMapping("/")
    fun hello(): String {
        manageFeature.create(FeatureInputDTO(
            name = "CRUD_USUARIO_ADMIN",
            description = "Feature p/ gerenciamento administrativo de usuáios"
        ))
        return "Hello"
    }
}