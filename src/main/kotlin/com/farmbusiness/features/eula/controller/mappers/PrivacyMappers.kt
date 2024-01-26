package com.farmbusiness.features.eula.controller.mappers

import com.farmbusiness.features.eula.controller.request.privacy.PostPrivacyPolicyRequest
import com.farmbusiness.features.eula.controller.request.privacy.PutPrivacyPolicyRequest
import com.farmbusiness.features.eula.controller.response.PrivacyPolicyResponse
import com.farmbusiness.features.eula.domain.privacy.PrivacyPolicyModel


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