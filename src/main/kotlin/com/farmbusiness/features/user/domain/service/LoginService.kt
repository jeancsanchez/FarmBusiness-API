package com.farmbusiness.features.user.domain.service

import com.farmbusiness.config.security.JwtUtil
import com.farmbusiness.config.security.UserCustomDetails
import com.farmbusiness.features.user.domain.model.UsersModel
import com.farmbusiness.errors.Errors
import com.farmbusiness.features.user.domain.errors.AuthenticationException
import com.farmbusiness.features.user.repository.UsersRepository
import org.springframework.security.authentication.AuthenticationManager
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
    fun login(login: String, password: String): Pair<String?, UsersModel?>? {
        val authResult = attemptAuthentication(login, password)

        SecurityContextHolder.getContext().authentication = authResult
        return successfulAuthentication(authResult)
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
            throw AuthenticationException(Errors.ML001.message, Errors.ML001.code)
        }
    }

    private fun successfulAuthentication(
        authResult: Authentication
    ): Pair<String?, UsersModel?> {
        val id = (authResult.principal as UserCustomDetails).id
        val user = id?.let { usersRepository.findById(it).get() }
        val token = id?.let {
            jwtUtil.generateToken(id)
        }

        return Pair(
            first = token,
            second = user?.apply { password = "" }
        )
    }
}