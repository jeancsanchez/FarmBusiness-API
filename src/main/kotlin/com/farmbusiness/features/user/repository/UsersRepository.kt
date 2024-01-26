package com.farmbusiness.features.user.repository

import com.farmbusiness.features.user.domain.model.UsersModel
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository : JpaRepository<UsersModel, Int> {
    fun findByEmail(email: String): UsersModel?
    fun findByCpf(cpf: String): UsersModel?
    fun findByCnpj(cnpj: String): UsersModel?
}