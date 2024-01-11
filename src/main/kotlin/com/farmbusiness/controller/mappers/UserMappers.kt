package com.farmbusiness.controller.mappers

import com.farmbusiness.controller.request.user.PostUsersRequest
import com.farmbusiness.domain.core.user.model.Role
import com.farmbusiness.domain.core.user.model.UsersModel

fun PostUsersRequest.toUsersModel(): UsersModel {
    return UsersModel(
        firstName = this.firstName,
        cpf = this.cpf,
        email = this.email,
        password = this.password,
        company = this.company,
        fantasyName = this.fantasyName,
        cnpj = this.cnpj,
        phone = this.phone,
        roles = setOf(
            if (this.type == PostUsersRequest.SELLER_ID) Role.SELLER else Role.BUYER
        )
    )
}
