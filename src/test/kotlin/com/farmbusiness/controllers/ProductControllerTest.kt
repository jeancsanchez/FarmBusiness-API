package com.farmbusiness.controllers

import com.farmbusiness.controller.request.product.ProductRequest
import com.farmbusiness.extension.set
import com.farmbusiness.extension.toBRFormat
import com.farmbusiness.repository.ProductRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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
import java.util.*

/**
 * @author @jeancsanchez
 * @created 18/12/2023
 * Jesus loves you.
 */

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner::class)
@TestExecutionListeners(DependencyInjectionTestExecutionListener::class)
class ProductControllerTest {

    @Autowired
    protected lateinit var context: WebApplicationContext

    private lateinit var mvc: MockMvc

    protected lateinit var request: MockHttpServletRequestBuilder

    @Autowired
    protected lateinit var productRepository: ProductRepository

    protected val mapper = ObjectMapper()

    @BeforeEach
    fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build()
        productRepository.deleteAll()
    }

    @Test
    fun createProductWithSuccessTest() {
        // Given
        val expectedShelfLife = Date().set(
            year = 2024,
            month = 10,
            day = 20,
            hour = 10,
            minute = 10,
            second = 0
        )

        val body = ProductRequest(
            title = "Product 1",
            description = "some description",
            presentation = "some presentation",
            images = emptyList(),
            code = "somecode",
            shelfLifeMillis = expectedShelfLife.time,
            batch = "FE1213435",
            unitPrice = 3.4,
            totalItems = 2
        )


        // When
        request = post("/products")
            .content(mapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON)

        // Then
        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().`is`(201))

        productRepository
            .findAll()
            .last()
            .run {
                assertEquals(body.title, title)
                assertEquals(body.description, description)
                assertEquals(body.presentation, presentation)
                assertTrue(body.images?.isEmpty() == true)
                assertEquals(body.code, code)
                assertEquals(
                    Date().set(second = 0).toBRFormat(),
                    createdAt.set(second = 0).toBRFormat()
                )
                assertEquals(expectedShelfLife.toBRFormat(), shelfLife.toBRFormat())
                assertEquals(body.batch, batch)
                assertEquals(body.unitPrice, unitPrice)
                assertEquals(body.totalItems, totalItems)
            }
    }
}