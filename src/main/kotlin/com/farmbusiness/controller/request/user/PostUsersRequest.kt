package com.farmbusiness.controller.request.user

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotEmpty

data class PostUsersRequest(

    @field:Range(min = 1, max = 2, message = "options for parameter `type`: seller - 1; buyer - 2")
    var type: Int,

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
) {
    companion object {
        const val SELLER_ID = 1
        const val BUYER_ID = 2
    }
}