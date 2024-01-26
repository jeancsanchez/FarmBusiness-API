package com.farmbusiness.features.user.domain.service

import com.farmbusiness.config.security.UserCustomDetails
import com.farmbusiness.features.user.domain.errors.AuthenticationException
import com.farmbusiness.features.user.repository.UsersRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val usersRepository: UsersRepository
): UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val user = usersRepository.findById(id.toInt()).orElseThrow { AuthenticationException("Usuário não encontrado", "999" ) }
        return UserCustomDetails(user)
    }
}