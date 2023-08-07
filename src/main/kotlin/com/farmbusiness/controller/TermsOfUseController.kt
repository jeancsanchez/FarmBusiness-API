package com.farmbusiness.controller

import com.farmbusiness.controller.request.PostTermsOfUseRequest
import com.farmbusiness.controller.request.PutTermsOfUseRequest
import com.farmbusiness.controller.response.TermsOfUseResponse
import com.farmbusiness.extension.toResponse
import com.farmbusiness.extension.toTermsOfUseModel
import com.farmbusiness.service.TermsOfUseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("terms-of-use")
class TermsOfUseController(
    private val termsOfUseService : TermsOfUseService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostTermsOfUseRequest) {
        termsOfUseService.create(request.toTermsOfUseModel())
    }

    @GetMapping
    fun find(): TermsOfUseResponse? {
        return termsOfUseService.findTermsOfUse()?.toResponse()
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete() {
        termsOfUseService.delete()
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@RequestBody request: PutTermsOfUseRequest) {
        termsOfUseService.update(request.toTermsOfUseModel())
    }
}