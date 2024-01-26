package com.farmbusiness.features.eula.repository

import com.farmbusiness.features.eula.domain.terms.TermsOfUseModel
import org.springframework.data.jpa.repository.JpaRepository

interface TermsOfUseRepository : JpaRepository<TermsOfUseModel, Int> {
    fun findByTermsOfUse(termsOfUse: TermsOfUseModel): List<TermsOfUseModel>
}