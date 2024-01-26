package com.farmbusiness.features.eula.controller

import com.farmbusiness.features.eula.controller.mappers.toAboutModel
import com.farmbusiness.features.eula.controller.mappers.toResponse
import com.farmbusiness.features.eula.controller.request.about.PostAboutRequest
import com.farmbusiness.features.eula.controller.request.about.PutAboutRequest
import com.farmbusiness.features.eula.controller.response.AboutResponse
import com.farmbusiness.features.eula.domain.about.AboutService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("about")
class AboutController(
        private val aboutService: AboutService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostAboutRequest) {
        aboutService.create(request.toAboutModel())
    }

    @GetMapping
    fun find(): AboutResponse? {
        return aboutService.findAbout()?.toResponse()
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete() {
        aboutService.delete()
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@RequestBody request: PutAboutRequest) {
        aboutService.update(request.toAboutModel())
    }
}