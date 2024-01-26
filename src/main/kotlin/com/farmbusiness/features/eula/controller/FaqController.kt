package com.farmbusiness.features.eula.controller

import com.farmbusiness.utils.extension.toPageResponse
import com.farmbusiness.features.eula.controller.mappers.toFaqModel
import com.farmbusiness.features.eula.controller.mappers.toResponse
import com.farmbusiness.features.eula.controller.request.faq.PostFaqRequest
import com.farmbusiness.features.eula.controller.request.faq.PutFaqRequest
import com.farmbusiness.features.eula.controller.response.FaqResponse
import com.farmbusiness.features.eula.controller.response.PageResponse
import com.farmbusiness.features.eula.domain.faq.FaqService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("faq")
class FaqController(
    private val faqService: FaqService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostFaqRequest) {
        faqService.create(request.toFaqModel())
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<FaqResponse> {
        return faqService.findAll(pageable)
            .map { it.toResponse() }
            .toPageResponse()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): FaqResponse {
        return faqService.findById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        faqService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutFaqRequest) {
        faqService.update(book.toFaqModel(id))
    }

}