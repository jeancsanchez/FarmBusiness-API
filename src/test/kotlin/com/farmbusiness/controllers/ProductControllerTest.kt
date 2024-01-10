package com.farmbusiness.controllers

import com.farmbusiness.controller.model.product.CategoryModel
import com.farmbusiness.controller.model.product.SubCategoryModel
import com.farmbusiness.controller.request.product.ProductRequest
import com.farmbusiness.extension.set
import com.farmbusiness.extension.toBRFormat
import com.farmbusiness.repository.CategoryRepository
import com.farmbusiness.repository.ProductRepository
import com.farmbusiness.repository.SubCategoryRepository
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

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
class ProductControllerTest {

    @Autowired
    protected lateinit var context: WebApplicationContext

    private lateinit var mvc: MockMvc

    protected lateinit var request: MockHttpServletRequestBuilder

    @Autowired
    protected lateinit var productRepository: ProductRepository

    @Autowired
    protected lateinit var categoryRepository: CategoryRepository

    @Autowired
    protected lateinit var subCategoryRepository: SubCategoryRepository

    private lateinit var mockCategory: CategoryModel
    private lateinit var mockSubcatgory: SubCategoryModel

    protected val mapper = ObjectMapper()

    @BeforeEach
    fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build()
        productRepository.deleteAll()
        mockCategory = categoryRepository.save(CategoryModel(3, "someCategory"))
        mockSubcatgory = subCategoryRepository.save(SubCategoryModel(3, "someSubCategory", mockCategory))
    }

    @Test
    fun `Criar novo Produto com sucesso`() {
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
            images = listOf(
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAApgAAAKYB3X3/OAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAANCSURBVEiJtZZPbBtFFMZ/M7ubXdtdb1xSFyeilBapySVU8h8OoFaooFSqiihIVIpQBKci6KEg9Q6H9kovIHoCIVQJJCKE1ENFjnAgcaSGC6rEnxBwA04Tx43t2FnvDAfjkNibxgHxnWb2e/u992bee7tCa00YFsffekFY+nUzFtjW0LrvjRXrCDIAaPLlW0nHL0SsZtVoaF98mLrx3pdhOqLtYPHChahZcYYO7KvPFxvRl5XPp1sN3adWiD1ZAqD6XYK1b/dvE5IWryTt2udLFedwc1+9kLp+vbbpoDh+6TklxBeAi9TL0taeWpdmZzQDry0AcO+jQ12RyohqqoYoo8RDwJrU+qXkjWtfi8Xxt58BdQuwQs9qC/afLwCw8tnQbqYAPsgxE1S6F3EAIXux2oQFKm0ihMsOF71dHYx+f3NND68ghCu1YIoePPQN1pGRABkJ6Bus96CutRZMydTl+TvuiRW1m3n0eDl0vRPcEysqdXn+jsQPsrHMquGeXEaY4Yk4wxWcY5V/9scqOMOVUFthatyTy8QyqwZ+kDURKoMWxNKr2EeqVKcTNOajqKoBgOE28U4tdQl5p5bwCw7BWquaZSzAPlwjlithJtp3pTImSqQRrb2Z8PHGigD4RZuNX6JYj6wj7O4TFLbCO/Mn/m8R+h6rYSUb3ekokRY6f/YukArN979jcW+V/S8g0eT/N3VN3kTqWbQ428m9/8k0P/1aIhF36PccEl6EhOcAUCrXKZXXWS3XKd2vc/TRBG9O5ELC17MmWubD2nKhUKZa26Ba2+D3P+4/MNCFwg59oWVeYhkzgN/JDR8deKBoD7Y+ljEjGZ0sosXVTvbc6RHirr2reNy1OXd6pJsQ+gqjk8VWFYmHrwBzW/n+uMPFiRwHB2I7ih8ciHFxIkd/3Omk5tCDV1t+2nNu5sxxpDFNx+huNhVT3/zMDz8usXC3ddaHBj1GHj/As08fwTS7Kt1HBTmyN29vdwAw+/wbwLVOJ3uAD1wi/dUH7Qei66PfyuRj4Ik9is+hglfbkbfR3cnZm7chlUWLdwmprtCohX4HUtlOcQjLYCu+fzGJH2QRKvP3UNz8bWk1qMxjGTOMThZ3kvgLI5AzFfo379UAAAAASUVORK5CYII="
            ),
            code = "somecode",
            shelfLifeMillis = expectedShelfLife.time,
            batch = "FE1213435",
            unitPrice = 3.4,
            totalItems = 2,
            categoryId = mockCategory.id!!,
            subCategoryId = mockSubcatgory.id!!
        )


        // When
        request = post("/products")
            .content(mapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON)


        // Then
        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().`is`(201))
            .andExpect(jsonPath("$.id").isNumber)
            .andExpect(jsonPath("$.title").value(body.title))
            .andExpect(jsonPath("$.description").value(body.description))
            .andExpect(jsonPath("$.images").isNotEmpty())
            .andExpect(jsonPath("$.presentation").value(body.presentation))
            .andExpect(jsonPath("$.batch").value(body.batch))
            .andExpect(jsonPath("$.unitPrice").value(body.unitPrice))
            .andExpect(jsonPath("$.totalItems").value(body.totalItems))

        productRepository
            .findAll()
            .last()
            .run {
                assertEquals(body.title, title)
                assertEquals(body.description, description)
                assertEquals(body.presentation, presentation)
                assertTrue(body.images?.isEmpty() == false)
                assertEquals(body.code, code)
                assertEquals(
                    Date().set(second = 0).toBRFormat(),
                    createdAt?.set(second = 0)?.toBRFormat()
                )
                assertEquals(expectedShelfLife.toBRFormat(), shelfLife.toBRFormat())
                assertEquals(body.batch, batch)
                assertEquals(body.unitPrice, unitPrice)
                assertEquals(body.totalItems, totalItems)
                assertEquals(body.subCategoryId, subcategory?.id)
            }
    }

    @Test
    fun `Valida Categoria correta ao criar novo Produto`() {
        // Given
        val body = ProductRequest(
            title = "Product 1",
            description = "some description",
            presentation = "some presentation",
            images = listOf(),
            code = "somecode",
            shelfLifeMillis = 1L,
            batch = "FE1213435",
            unitPrice = 3.4,
            totalItems = 2,
            categoryId = -1, // <-------------
            subCategoryId = mockSubcatgory.id!!
        )


        // When
        request = post("/products")
            .content(mapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON)


        // Then
        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().`is`(404))
    }

    @Test
    fun `Valida SubCategoria correta ao criar novo Produto`() {
        // Given
        val body = ProductRequest(
            title = "Product 1",
            description = "some description",
            presentation = "some presentation",
            images = listOf(),
            code = "somecode",
            shelfLifeMillis = 1L,
            batch = "FE1213435",
            unitPrice = 3.4,
            totalItems = 2,
            categoryId = mockCategory.id!!,
            subCategoryId = -1 // <-------------
        )


        // When
        request = post("/products")
            .content(mapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON)


        // Then
        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().`is`(404))
    }
}