package com.farmbusiness.repository

import com.farmbusiness.domain.core.privacy.PrivacyPolicyModel
import org.springframework.data.jpa.repository.JpaRepository

interface PrivacyPolicyRepository : JpaRepository<PrivacyPolicyModel, Int> {
    fun findByPrivacyPolicy(privacyPolicy: PrivacyPolicyModel): List<PrivacyPolicyModel>

}