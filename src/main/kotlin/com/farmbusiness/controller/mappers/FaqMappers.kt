package com.farmbusiness.controller.mappers

import com.farmbusiness.controller.request.faq.PostFaqRequest
import com.farmbusiness.controller.request.faq.PutFaqRequest
import com.farmbusiness.controller.response.FaqResponse
import com.farmbusiness.domain.core.faq.FaqModel

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

