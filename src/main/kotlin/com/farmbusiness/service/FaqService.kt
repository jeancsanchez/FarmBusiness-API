package com.farmbusiness.service

import com.farmbusiness.controller.exception.NotFoundException
import com.farmbusiness.controller.model.FaqModel
import com.farmbusiness.enums.Errors
import com.farmbusiness.repository.FaqRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FaqService(
    private val faqRepository: FaqRepository
) {

    fun create(faq: FaqModel) {
        faqRepository.save(faq)
    }

    fun findAll(pageable: Pageable): Page<FaqModel> {
        return faqRepository.findAll(pageable)
    }

    fun findById(id: Int): FaqModel {
        return faqRepository.findById(id).orElseThrow{ NotFoundException(Errors.ML101.message.format(id), Errors.ML101.code) }
    }

    fun delete(id: Int) {
        val faq = findById(id)
        update(faq)
    }

    fun update(faq: FaqModel) {
        faqRepository.save(faq)
    }
}
