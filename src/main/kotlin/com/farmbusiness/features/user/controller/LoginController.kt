package com.farmbusiness.controller

import com.farmbusiness.controller.mappers.toLoginResponse
import com.farmbusiness.controller.request.user.LoginRequest
import com.farmbusiness.controller.response.LoginResponse
import com.farmbusiness.domain.core.user.service.LoginService
import com.farmbusiness.utils.extension.BEARER
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author @jeancsanchez
 * @created 23/12/2023
 * Jesus loves you.
 */

@RestController
@RequestMapping
class LoginController(
    private val loginService: LoginService
) {
    @PostMapping("/login")
    fun login(@RequestBody @Valid body: LoginRequest): ResponseEntity<LoginResponse> {
        loginService
            .login(body.emailOrCpfOrCnpj, body.password)
            ?.toLoginResponse()
            ?.let { data ->
                return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE))
                    .header(HttpHeaders.AUTHORIZATION, "$BEARER ${data.token}")
                    .body(data)
            }
            ?: let {
                return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build()
            }
    }
}