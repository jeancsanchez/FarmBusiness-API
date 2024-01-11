package com.farmbusiness.domain.core.user.service

import com.farmbusiness.domain.core.user.model.UsersModel
import com.farmbusiness.domain.core.user.model.UsersStatus
import com.farmbusiness.domain.errors.Errors
import com.farmbusiness.domain.errors.exceptions.NotFoundException
import com.farmbusiness.repository.UsersRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val usersRepository: UsersRepository,
    private val bCrypt: BCryptPasswordEncoder
) {

    fun create(usersModel: UsersModel) {
        val user = usersModel.apply {
            status = UsersStatus.ATIVO
            password = bCrypt.encode(usersModel.password)
        }

        usersRepository.save(user)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        customer.status = UsersStatus.INATIVO

        usersRepository.save(customer)
    }

    fun findById(id: Int): UsersModel {
        return usersRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code) }
    }
}