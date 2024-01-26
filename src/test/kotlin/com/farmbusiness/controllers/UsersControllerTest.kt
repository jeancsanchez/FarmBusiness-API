package com.farmbusiness.controllers

import com.farmbusiness.features.user.controller.request.PostUsersRequest
import com.farmbusiness.features.user.domain.model.Role
import com.farmbusiness.features.user.domain.model.UsersModel
import com.farmbusiness.features.user.domain.model.UsersStatus
import com.farmbusiness.features.user.repository.UsersRepository
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

/**
 * @author @jeancsanchez
 * @created 18/12/2023
 * Jesus loves you.
 */

@Suppress("SpellCheckingInspection")
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

    private val mockUsersRequest = PostUsersRequest(
        type = PostUsersRequest.SELLER_ID,
        firstName = "name",
        cpf = "some",
        email = "someEmail@gmail.com",
        password = "somepass",
        company = "somecompany",
        fantasyName = "fantasyname",
        cnpj = "cnpj",
        phone = "4232332"
    )


    @BeforeEach
    fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build()
        usersRepository.deleteAll()
    }

    @Test
    fun `Cria novo usuario com todos os dados corretos`() {
        // Given
        val body = mockUsersRequest.copy(type = PostUsersRequest.BUYER_ID)

        // When
        request = post("/users")
            .content(mapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON)

        // Then
        mvc.perform(request)
            .andExpect(status().`is`(201))
            .andExpect(jsonPath("$.id").isNumber)
            .andExpect(jsonPath("$.roles").doesNotExist())
            .andExpect(jsonPath("$.firstName").value(body.firstName))
            .andExpect(jsonPath("$.cpf").value(body.cpf))
            .andExpect(jsonPath("$.email").value(body.email))
            .andExpect(jsonPath("$.password").doesNotExist())
            .andExpect(jsonPath("$.company").value(body.company))
            .andExpect(jsonPath("$.fantasyName").value(body.fantasyName))

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

    @Test
    fun `Valida email duplicado ao criar novo usuario`() {
        // Given
        val mockEmail = "test@gmail.com"
        val body = mockUsersRequest.copy(email = mockEmail, cpf = "1", cnpj = "2")
        val userModel = mockUsersRequest.run {
            UsersModel(
                roles = setOf(Role.BUYER),
                email = mockEmail,
                cpf = "3",
                cnpj = "4",
                status = UsersStatus.ATIVO,
                firstName = firstName,
                password = password,
                company = company,
                fantasyName = fantasyName,
                phone = phone,
            )
        }

        usersRepository.save(userModel)


        // When
        request = post("/users")
            .content(mapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON)

        // Then
        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().`is`(409))

        assertEquals(1, usersRepository.count())
    }

    @Test
    fun `Valida cpf duplicado ao criar novo usuario`() {
        // Given
        val mockCPF = "99999999"
        val body = mockUsersRequest.copy(cpf = mockCPF, cnpj = "1", email = "2")
        val userModel = mockUsersRequest.run {
            UsersModel(
                roles = setOf(Role.BUYER),
                cpf = mockCPF,
                cnpj = "3",
                email = "4",
                status = UsersStatus.ATIVO,
                firstName = firstName,
                password = password,
                company = company,
                fantasyName = fantasyName,
                phone = phone,
            )
        }

        usersRepository.save(userModel)


        // When
        request = post("/users")
            .content(mapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON)

        // Then
        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().`is`(409))

        assertEquals(1, usersRepository.count())
    }

    @Test
    fun `Valida cnpj duplicado ao criar novo usuario`() {
        // Given
        val mockCNPJ = "99999999"
        val body = mockUsersRequest.copy(cnpj = mockCNPJ, cpf = "1", email = "2")
        val userModel = mockUsersRequest.run {
            UsersModel(
                roles = setOf(Role.BUYER),
                cnpj = mockCNPJ,
                cpf = "3",
                email = "4",
                status = UsersStatus.ATIVO,
                firstName = firstName,
                password = password,
                company = company,
                fantasyName = fantasyName,
                phone = phone,
            )
        }

        usersRepository.save(userModel)


        // When
        request = post("/users")
            .content(mapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON)

        // Then
        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().`is`(409))

        assertEquals(1, usersRepository.count())
    }
}