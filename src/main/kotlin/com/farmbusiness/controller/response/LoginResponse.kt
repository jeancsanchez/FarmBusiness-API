package com.farmbusiness.controller.response

import com.farmbusiness.controller.model.UsersModel

data class LoginResponse(
    val token: String?,
    val userData: UsersModel?
)