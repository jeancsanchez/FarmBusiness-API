package com.farmbusiness.service

import com.farmbusiness.controller.model.TermsOfUseModel
import com.farmbusiness.repository.TermsOfUseRepository
import org.springframework.stereotype.Service

@Service
class TermsOfUseService(
    private val termsOfUseRepository: TermsOfUseRepository
) {

    fun create(termsOfUse: TermsOfUseModel) {
        createNewOrUpdate(termsOfUse)
    }

    fun findTermsOfUse(): TermsOfUseModel? {
        return termsOfUseRepository.findAll().firstOrNull()
    }

    fun delete() {
        termsOfUseRepository.deleteAll()
    }

    fun update(termsOfUse: TermsOfUseModel) {
        createNewOrUpdate(termsOfUse)
    }

    fun createNewOrUpdate(termsOfUse: TermsOfUseModel) {
        termsOfUseRepository.findAll().firstOrNull()?.let {
            termsOfUse.id = it.id
        }
        termsOfUseRepository.save(termsOfUse)
    }
}