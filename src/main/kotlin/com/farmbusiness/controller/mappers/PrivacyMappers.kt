package com.farmbusiness.controller.mappers

import com.farmbusiness.controller.request.privacy.PostPrivacyPolicyRequest
import com.farmbusiness.controller.request.privacy.PutPrivacyPolicyRequest
import com.farmbusiness.controller.response.PrivacyPolicyResponse
import com.farmbusiness.domain.core.privacy.PrivacyPolicyModel


fun PrivacyPolicyModel.toResponse(): PrivacyPolicyResponse {
    return PrivacyPolicyResponse(
        id = this.id,
        privacyPolicy = this.privacyPolicy
    )
}

fun PostPrivacyPolicyRequest.toPrivacyPolicyModel(): PrivacyPolicyModel {
    return PrivacyPolicyModel(
        privacyPolicy = this.privacyPolicy
    )
}

fun PutPrivacyPolicyRequest.toPrivacyPolicyModel(): PrivacyPolicyModel {
    return PrivacyPolicyModel(
        privacyPolicy = this.privacyPolicy
    )
}