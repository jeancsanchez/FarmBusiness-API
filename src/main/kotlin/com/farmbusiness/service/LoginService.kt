package com.farmbusiness.service

import com.farmbusiness.config.security.JwtUtil
import com.farmbusiness.config.security.UserCustomDetails
import com.farmbusiness.controller.response.LoginResponse
import com.farmbusiness.exception.AuthenticationException
import com.farmbusiness.repository.UsersRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val authenticationManager: AuthenticationManager,
    private val usersRepository: UsersRepository,
    private val jwtUtil: JwtUtil
) {
    fun login(login: String, password: String): LoginResponse? {
        return try {
            val authResult = attemptAuthentication(login, password)

            SecurityContextHolder.getContext().authentication = authResult
            successfulAuthentication(authResult)
        } catch (t: Throwable) {
            null
        }
    }

    private fun attemptAuthentication(login: String, password: String): Authentication {
        try {
            val id = when {
                login
                    .replace(".", "")
                    .replace("-", "")
                    .length == 11 -> usersRepository.findByCpf(login)?.id

                login
                    .replace(".", "")
                    .replace("-", "")
                    .replace("/", "")
                    .length == 14 -> usersRepository.findByCnpj(login)?.id

                else -> usersRepository.findByEmail(login)?.id
            }

            val authToken = UsernamePasswordAuthenticationToken(id, password)
            return authenticationManager.authenticate(authToken)
        } catch (ex: Exception) {
            ex.printStackTrace()
            throw AuthenticationException("Falha ao autenticar", "999")
        }
    }

    private fun successfulAuthentication(
        authResult: Authentication
    ): LoginResponse {
        val id = (authResult.principal as UserCustomDetails).id
        val user = id?.let { usersRepository.findById(it).get() }
        val token = id?.let {
            jwtUtil.generateToken(id)
        }

        return LoginResponse(
            token = token,
            userData = user?.copy(password = "")
        )
    }
}