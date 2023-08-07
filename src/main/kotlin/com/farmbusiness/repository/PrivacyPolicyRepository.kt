package com.farmbusiness.repository

import com.farmbusiness.controller.model.PrivacyPolicyModel
import org.springframework.data.jpa.repository.JpaRepository

interface PrivacyPolicyRepository : JpaRepository<PrivacyPolicyModel, Int> {
    fun findByPrivacyPolicy(privacyPolicy: PrivacyPolicyModel): List<PrivacyPolicyModel>

}