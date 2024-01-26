package com.farmbusiness.controller.mappers

import com.farmbusiness.controller.request.terms.PostTermsOfUseRequest
import com.farmbusiness.controller.request.terms.PutTermsOfUseRequest
import com.farmbusiness.controller.response.TermsOfUseResponse
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


