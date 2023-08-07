package com.farmbusiness.controller.request

import javax.validation.constraints.NotEmpty

data class PostFaqRequest (

    @field:NotEmpty(message = "Título deve ser informado")
    var title: String,

    @field:NotEmpty(message = "Descrição deve ser informado")
    var description: String,

)