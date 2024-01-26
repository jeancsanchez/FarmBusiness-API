package com.farmbusiness.controller

import com.farmbusiness.controller.mappers.toPrivacyPolicyModel
import com.farmbusiness.controller.mappers.toResponse
import com.farmbusiness.controller.request.privacy.PostPrivacyPolicyRequest
import com.farmbusiness.controller.request.privacy.PutPrivacyPolicyRequest
import com.farmbusiness.controller.response.PrivacyPolicyResponse
import com.farmbusiness.domain.core.privacy.PrivacyPolicyService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("privacy-policy")
class PrivacyPolicyController(
    private val privacyPolicyService: PrivacyPolicyService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostPrivacyPolicyRequest) {
        privacyPolicyService.create(request.toPrivacyPolicyModel())
    }

    @GetMapping
    fun find(): PrivacyPolicyResponse? {
        return privacyPolicyService.findPrivacyPolicy()?.toResponse()
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete() {
        privacyPolicyService.delete()
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@RequestBody request: PutPrivacyPolicyRequest) {
        privacyPolicyService.update(request.toPrivacyPolicyModel())
    }
}