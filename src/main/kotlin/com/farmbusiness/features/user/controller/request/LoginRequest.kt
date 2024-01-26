package com.farmbusiness.controller.request.user

import javax.validation.constraints.NotNull

data class LoginRequest(
    @NotNull val emailOrCpfOrCnpj: String,
    @NotNull val password: String
)