package com.farmbusiness.controllers

import com.farmbusiness.controller.request.user.PostUsersRequest
import com.farmbusiness.domain.core.user.model.Role
import com.farmbusiness.domain.core.user.model.UsersStatus
import com.farmbusiness.repository.UsersRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

/**
 * @author @jeancsanchez
 * @created 18/12/2023
 * Jesus loves you.
 */

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner::class)
@TestExecutionListeners(DependencyInjectionTestExecutionListener::class)
class UsersControllerTest {

    @Autowired
    protected lateinit var context: WebApplicationContext

    private lateinit var mvc: MockMvc

    protected lateinit var request: MockHttpServletRequestBuilder

    @Autowired
    protected lateinit var usersRepository: UsersRepository

    protected val mapper = ObjectMapper()

    @BeforeEach
    fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build()
        usersRepository.deleteAll()
    }

    @Test
    fun createUserWithSuccessTest() {
        // Given
        val body = PostUsersRequest(
            type = "buyer",
            firstName = "name",
            cpf = "some",
            email = "someemail",
            password = "somepass",
            company = "somecompany",
            fantasyName = "fantasyname",
            cnpj = "cnpj",
            phone = "4232332"
        )


        // When
        request = post("/users")
            .content(mapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON)

        // Then
        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().`is`(201))

        usersRepository
            .findAll()
            .last()
            .run {
                assertEquals(UsersStatus.ATIVO, status)
                assertEquals(Role.BUYER, roles.first())
                assertEquals(body.firstName, firstName)
                assertEquals(body.firstName, firstName)
                assertEquals(body.cpf, cpf)
                assertEquals(body.email, email)
                assertNotNull(password)
                assertEquals(body.company, company)
                assertEquals(body.fantasyName, fantasyName)
                assertEquals(body.cnpj, cnpj)
                assertEquals(body.phone, phone)
            }
    }
}