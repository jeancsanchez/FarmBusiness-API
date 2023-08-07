package com.farmbusiness.controller.request

import javax.validation.constraints.NotEmpty

data class PostAboutRequest (

    @field:NotEmpty(message = "Sobre deve ser informado")
    var about: String

)