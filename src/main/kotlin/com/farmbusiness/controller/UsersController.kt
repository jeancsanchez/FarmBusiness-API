package com.farmbusiness.controller

import com.farmbusiness.config.security.UserCanOnlyAccessTheirOwnResource
import com.farmbusiness.controller.mappers.toUsersModel
import com.farmbusiness.controller.request.user.PostUsersRequest
import com.farmbusiness.domain.core.user.model.UsersModel
import com.farmbusiness.domain.core.user.service.UsersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
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
    fun create(@RequestBody @Valid request: PostUsersRequest): ResponseEntity<UsersModel> {
        val model = usersService.create(request.toUsersModel())

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(model)
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