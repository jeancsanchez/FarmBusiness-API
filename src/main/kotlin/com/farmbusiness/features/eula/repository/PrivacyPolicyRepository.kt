package com.farmbusiness.features.eula.repository

import com.farmbusiness.features.eula.domain.privacy.PrivacyPolicyModel
import org.springframework.data.jpa.repository.JpaRepository

interface PrivacyPolicyRepository : JpaRepository<PrivacyPolicyModel, Int> {
    fun findByPrivacyPolicy(privacyPolicy: PrivacyPolicyModel): List<PrivacyPolicyModel>

}