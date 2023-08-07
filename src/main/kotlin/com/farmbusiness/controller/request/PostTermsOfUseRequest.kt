package com.farmbusiness.controller.request

import javax.validation.constraints.NotEmpty

data class PostTermsOfUseRequest (

    @field:NotEmpty(message = "Termos de Uso deve ser informado")
    var termsOfUse: String
)