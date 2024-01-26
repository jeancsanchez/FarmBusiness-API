package com.farmbusiness.features.eula.controller.request.about

import javax.validation.constraints.NotEmpty

data class PostAboutRequest(

        @field:NotEmpty(message = "Sobre deve ser informado")
        var about: String

)