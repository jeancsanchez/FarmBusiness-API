package com.farmbusiness.features.user.controller.response

import com.farmbusiness.features.user.domain.model.UsersModel

data class LoginResponse(
        val token: String?,
        val userData: UsersModel?
)