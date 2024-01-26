package com.farmbusiness.features.eula.controller.mappers

import com.farmbusiness.features.eula.controller.request.faq.PostFaqRequest
import com.farmbusiness.features.eula.controller.request.faq.PutFaqRequest
import com.farmbusiness.features.eula.controller.response.FaqResponse
import com.farmbusiness.features.eula.domain.faq.FaqModel

fun FaqModel.toResponse(): FaqResponse {
    return FaqResponse(
            id = this.id,
            title = this.title,
            description = this.description
    )
}

fun PostFaqRequest.toFaqModel(): FaqModel {
    return FaqModel(
            title = this.title,
            description = this.description
    )
}

fun PutFaqRequest.toFaqModel(id: Int): FaqModel {
    return FaqModel(
            id = id,
            title = this.title,
            description = this.description
    )
}

