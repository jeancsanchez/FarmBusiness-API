package com.farmbusiness.config.security

import com.farmbusiness.controller.model.UsersModel
import com.farmbusiness.controller.request.LoginRequest
import com.farmbusiness.exception.AuthenticationException
import com.farmbusiness.repository.UsersRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val usersRepository: UsersRepository,
    private val jwtUtil: JwtUtil
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)

            val id = if(loginRequest.emailOrCpfOrCnpj.replace(".", "").replace("-", "").length == 11) {
                usersRepository.findByCpf(loginRequest.emailOrCpfOrCnpj)?.id
            } else if(loginRequest.emailOrCpfOrCnpj.replace(".", "").replace("-", "").replace("/", "").length == 14) {
                usersRepository.findByCnpj(loginRequest.emailOrCpfOrCnpj)?.id
            } else {
                usersRepository.findByEmail(loginRequest.emailOrCpfOrCnpj)?.id
            }

            val authToken = UsernamePasswordAuthenticationToken(id, loginRequest.password)
            return authenticationManager.authenticate(authToken)
        } catch (ex: Exception) {
            throw AuthenticationException("Falha ao autenticar", "999")
        }
    }

    private data class LoginResponse(
        val token: String?,
        val userData: UsersModel?
    )

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val id = (authResult.principal as UserCustomDetails).id
        val user = id?.let { usersRepository.findById(it).get() }
        val token = id?.let {
            jwtUtil.generateToken(id)
        }
        val data = LoginResponse(
            token = token,
            userData = user?.copy(password = "")
        )

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer $token")
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        response.writer.write(
            ObjectMapper().writeValueAsString(data)
        )
    }
}