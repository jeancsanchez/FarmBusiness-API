package com.farmbusiness.domain.core.privacy

import com.farmbusiness.repository.PrivacyPolicyRepository
import org.springframework.stereotype.Service

@Service
class PrivacyPolicyService(
    private val privacyPolicyRepository: PrivacyPolicyRepository
) {

    fun create(privacyPolicy: PrivacyPolicyModel) {
        createNewOrUpdate(privacyPolicy)
    }

    fun findPrivacyPolicy(): PrivacyPolicyModel? {
        return privacyPolicyRepository.findAll().firstOrNull()
    }

    fun delete() {
        privacyPolicyRepository.deleteAll()
    }

    fun update(privacyPolicy: PrivacyPolicyModel) {
        createNewOrUpdate(privacyPolicy)
    }

    fun createNewOrUpdate(privacyPolicy: PrivacyPolicyModel) {
        privacyPolicyRepository.findAll().firstOrNull()?.let {
            privacyPolicy.id = it.id
        }
        privacyPolicyRepository.save(privacyPolicy)
    }

}
