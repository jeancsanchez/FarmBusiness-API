package com.farmbusiness.repository

import com.farmbusiness.domain.core.user.model.UsersModel
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository : JpaRepository<UsersModel, Int> {
    fun findByEmail(email: String): UsersModel?
    fun findByCpf(cpf: String): UsersModel?
    fun findByCnpj(cnpj: String): UsersModel?
}