package com.farmbusiness.config.security

import com.farmbusiness.controller.model.UsersModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails(
    private val usersModel: UsersModel
): UserDetails {
    val id = usersModel.id

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = usersModel.roles.map { SimpleGrantedAuthority(it.description) }.toMutableList()

    override fun getPassword(): String = usersModel.password

    override fun getUsername(): String = usersModel.id.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}