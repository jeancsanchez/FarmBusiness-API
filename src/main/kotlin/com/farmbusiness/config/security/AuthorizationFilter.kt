package com.farmbusiness.config.security

import com.farmbusiness.features.user.domain.service.UserDetailsCustomService
import com.farmbusiness.features.user.domain.errors.AuthenticationException
import com.farmbusiness.utils.extension.BEARER
import com.farmbusiness.utils.extension.sendUnauthorized
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationFilter(
        authenticationManager: AuthenticationManager,
        private val userDetails: UserDetailsCustomService,
        private val jwtUtil: JwtUtil
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorization = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authorization == null || !authorization.startsWith(BEARER)) {
            response.sendUnauthorized()
            return
        }

        try {
            val auth = getAuthentication(authorization.split(" ")[1])
            SecurityContextHolder.getContext().authentication = auth
            chain.doFilter(request, response)
        } catch (ex: AuthenticationException) {
            response.sendUnauthorized(ex)
        }
    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        if (!jwtUtil.isValidToken(token)) {
            throw AuthenticationException()
        }

        return try {
            val subject = jwtUtil.getSubject(token)
            val user = userDetails.loadUserByUsername(subject)
            UsernamePasswordAuthenticationToken(user, null, user.authorities)
        } catch (e: Exception) {
            throw AuthenticationException()
        }
    }
}