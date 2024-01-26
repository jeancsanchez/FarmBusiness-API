package com.farmbusiness.features.user.controller.request

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotEmpty

data class PostUsersRequest(

    @field:Range(min = 1, max = 2, message = "Options for field `type` are: Seller - 1; Buyer - 2")
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