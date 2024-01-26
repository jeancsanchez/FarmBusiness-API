package com.farmbusiness.features.eula.controller.request.terms

import javax.validation.constraints.NotEmpty

data class PostTermsOfUseRequest (

    @field:NotEmpty(message = "Termos de Uso deve ser informado")
    var termsOfUse: String
)