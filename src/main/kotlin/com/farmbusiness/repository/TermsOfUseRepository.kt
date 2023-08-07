package com.farmbusiness.repository

import com.farmbusiness.controller.model.TermsOfUseModel
import org.springframework.data.jpa.repository.JpaRepository

interface TermsOfUseRepository : JpaRepository<TermsOfUseModel, Int> {
    fun findByTermsOfUse(termsOfUse: TermsOfUseModel): List<TermsOfUseModel>
}