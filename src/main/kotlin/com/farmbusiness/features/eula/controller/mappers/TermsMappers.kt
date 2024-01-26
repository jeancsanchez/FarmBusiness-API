package com.farmbusiness.features.eula.controller.mappers

import com.farmbusiness.features.eula.controller.request.terms.PostTermsOfUseRequest
import com.farmbusiness.features.eula.controller.request.terms.PutTermsOfUseRequest
import com.farmbusiness.features.eula.controller.response.TermsOfUseResponse
import com.farmbusiness.features.eula.domain.terms.TermsOfUseModel

fun TermsOfUseModel.toResponse(): TermsOfUseResponse {
    return TermsOfUseResponse(
            id = this.id,
            termsOfUse = this.termsOfUse
    )
}

fun PostTermsOfUseRequest.toTermsOfUseModel(): TermsOfUseModel {
    return TermsOfUseModel(
            termsOfUse = this.termsOfUse
    )
}

fun PutTermsOfUseRequest.toTermsOfUseModel(): TermsOfUseModel {
    return TermsOfUseModel(
            termsOfUse = this.termsOfUse
    )
}


