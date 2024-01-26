package com.farmbusiness.features.eula.controller.mappers

import com.farmbusiness.features.eula.controller.request.about.PostAboutRequest
import com.farmbusiness.features.eula.controller.request.about.PutAboutRequest
import com.farmbusiness.features.eula.controller.response.AboutResponse
import com.farmbusiness.features.eula.domain.about.AboutModel

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
