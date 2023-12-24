package com.farmbusiness.controller

import com.farmbusiness.controller.request.LoginRequest
import com.farmbusiness.controller.response.LoginResponse
import com.farmbusiness.extension.BEARER
import com.farmbusiness.service.LoginService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
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