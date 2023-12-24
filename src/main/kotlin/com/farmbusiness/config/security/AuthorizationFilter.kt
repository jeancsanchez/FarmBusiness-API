package com.farmbusiness.config.security

import com.farmbusiness.enums.Role
import com.farmbusiness.extension.BEARER
import com.farmbusiness.exception.AuthenticationException
import com.farmbusiness.extension.sendUnauthorized
import com.farmbusiness.service.UserDetailsCustomService
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
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
        } catch (e: ExpiredJwtException) {
            e.printStackTrace()
            response.sendUnauthorized()
        }
    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        if (!jwtUtil.isValidToken(token)) {
            throw AuthenticationException("Token inválido", "999")
        }
        val subject = jwtUtil.getSubject(token)
        val user = userDetails.loadUserByUsername(subject)
        return UsernamePasswordAuthenticationToken(user, null, user.authorities)
    }
}