package com.farmbusiness.controller.request.user

import javax.validation.constraints.NotEmpty

data class PostUsersRequest (

    @field:NotEmpty(message = "O Tipo da conta deve ser informado")
    var type: String,

    var firstName: String? = null,

    var cpf: String? = null,

    @field:NotEmpty(message = "Email deve ser informado")
    var email: String,

    @field:NotEmpty(message = "Senha deve ser informado")
    var password: String,

    var company: String? = null,

    var fantasyName: String? = null,

    var cnpj: String? = null,

    var phone: String? = null,

)