package com.farmbusiness.controller.mappers

import com.farmbusiness.controller.request.about.PostAboutRequest
import com.farmbusiness.controller.request.about.PutAboutRequest
import com.farmbusiness.controller.response.AboutResponse
import com.farmbusiness.domain.core.about.AboutModel

fun AboutModel.toResponse(): AboutResponse {
    return AboutResponse(
        id = this.id,
        about = this.about
    )
}

fun PostAboutRequest.toAboutModel(): AboutModel {
    return AboutModel(
        about = this.about
    )
}

fun PutAboutRequest.toAboutModel(): AboutModel {
    return AboutModel(
        about = this.about
    )
}
