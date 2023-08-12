package com.farmbusiness.controller

import com.farmbusiness.config.security.UserCanOnlyAccessTheirOwnResource
import com.farmbusiness.controller.model.UsersModel
import com.farmbusiness.controller.request.PostUsersRequest
import com.farmbusiness.extension.toUsersModel
import com.farmbusiness.service.UsersService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("users")
@EnableGlobalMethodSecurity(prePostEnabled = true)
class UsersController(
    private val usersService: UsersService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostUsersRequest) {
        usersService.create(request.toUsersModel())
    }

    @GetMapping("/{id}")
    @UserCanOnlyAccessTheirOwnResource
    fun getUser(@PathVariable id: Int) {

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UserCanOnlyAccessTheirOwnResource
    fun delete(@PathVariable id: Int) {
        usersService.delete(id)
    }
}