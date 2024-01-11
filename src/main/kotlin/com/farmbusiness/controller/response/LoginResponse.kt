package com.farmbusiness.controller.response

import com.farmbusiness.domain.core.user.model.UsersModel

data class LoginResponse(
    val token: String?,
    val userData: UsersModel?
)