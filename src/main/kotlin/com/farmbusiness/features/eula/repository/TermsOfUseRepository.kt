package com.farmbusiness.repository

import com.farmbusiness.domain.core.terms.TermsOfUseModel
import org.springframework.data.jpa.repository.JpaRepository

interface TermsOfUseRepository : JpaRepository<TermsOfUseModel, Int> {
    fun findByTermsOfUse(termsOfUse: TermsOfUseModel): List<TermsOfUseModel>
}