package com.farmbusiness.controllers

import com.farmbusiness.features.product.domain.model.ProductImageModel
import com.farmbusiness.features.product.domain.model.ProductModel
import com.farmbusiness.features.product.domain.model.categories.CategoryModel
import com.farmbusiness.features.product.domain.model.categories.SubCategoryModel
import com.farmbusiness.features.product.repository.ProductRepository
import com.farmbusiness.features.product.repository.categories.CategoryRepository
import com.farmbusiness.features.product.repository.categories.SubCategoryRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

/**
 * @author @jeancsanchez
 * @created 18/12/2023ø
 * Jesus loves you.
 */

@Suppress("SpellCheckingInspection")
@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner::class)
@TestExecutionListeners(DependencyInjectionTestExecutionListener::class)
class HomeControllerTest {

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

    protected val mapper = ObjectMapper()

    @BeforeAll
    fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build()

        val category = CategoryModel(3, "someCategory")
        val subCategory = SubCategoryModel(3, "someSubCategory", category)

        categoryRepository.save(category)
        subCategoryRepository.save(subCategory)
        productRepository.save(
                ProductModel(
                        title = "Product 1",
                        description = "some description",
                        presentation = "some presentation",
                        images = listOf(
                                ProductImageModel(
                                        id = 1,
                                        imageUrl = "someurl"
                                )
                        ),
                        code = "somecode",
                        createdAt = Date(),
                        shelfLife = Date(),
                        batch = "FE1213435",
                        unitPrice = 3.4,
                        totalItems = 2,
                        subcategory = subCategory
                )
        )
    }


    @Test
    fun `GET Home Mobile com sucesso`() {
        // Given


        // When
        request = get("/home/mobile")

        // Then
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().`is`(201))
                .andExpect(jsonPath("$.banners").isArray)
                .andExpect(jsonPath("$.banners[0].image").isString)
                .andExpect(jsonPath("$.banners[0].rota").isString)
                .andExpect(jsonPath("$.banners[0].deeplink").isString)

                .andExpect(jsonPath("$.offerts").isArray)
                .andExpect(jsonPath("$.offerts[0].id").isNumber)
                .andExpect(jsonPath("$.offerts[0].title").isString)
                .andExpect(jsonPath("$.offerts[0].description").isString)
                .andExpect(jsonPath("$.offerts[0].type").isString)
                .andExpect(jsonPath("$.offerts[0].type").value("OFFER_PRODUCT"))
                .andExpect(jsonPath("$.offerts[0].products").isArray)
                .andExpect(jsonPath("$.offerts[0].products[0].name").isString)
                .andExpect(jsonPath("$.offerts[0].products[0].image").isString)
                .andExpect(jsonPath("$.offerts[0].products[0].rating").isNumber)
                .andExpect(jsonPath("$.offerts[0].products[0].priceInTotal").isNumber)
                .andExpect(jsonPath("$.offerts[0].products[0].priceInChash").isNumber)
                .andExpect(jsonPath("$.offerts[0].products[0].priceOnTime").isString)
                .andExpect(jsonPath("$.offerts[0].products[0].favorite").isBoolean)
                .andExpect(jsonPath("$.offerts[0].products[0].discount").isNumber)

                .andExpect(jsonPath("$.offerts[1].id").isNumber)
                .andExpect(jsonPath("$.offerts[1].title").isString)
                .andExpect(jsonPath("$.offerts[1].description").isString)
                .andExpect(jsonPath("$.offerts[1].type").isString)
                .andExpect(jsonPath("$.offerts[1].type").value("OFFER_SEEDS"))
                .andExpect(jsonPath("$.offerts[1].category").isArray)
                .andExpect(jsonPath("$.offerts[1].category[0].id").isNumber)
                .andExpect(jsonPath("$.offerts[1].category[0].title").isString)
                .andExpect(jsonPath("$.offerts[1].category[0].image").isNumber)

                .andExpect(jsonPath("$.offerts[2].id").isNumber)
                .andExpect(jsonPath("$.offerts[2].title").isString)
                .andExpect(jsonPath("$.offerts[2].description").isString)
                .andExpect(jsonPath("$.offerts[2].type").isString)
                .andExpect(jsonPath("$.offerts[2].type").value("OFFER_MOBILE"))
                .andExpect(jsonPath("$.offerts[2].iosRedirect").doesNotExist())
                .andExpect(jsonPath("$.offerts[2].androidRedirect").isString)

                .andExpect(jsonPath("$.offerts[3].id").isNumber)
                .andExpect(jsonPath("$.offerts[3].title").isString)
                .andExpect(jsonPath("$.offerts[3].description").isString)
                .andExpect(jsonPath("$.offerts[3].type").isString)
                .andExpect(jsonPath("$.offerts[3].type").value("OFFER_NEWS"))
                .andExpect(jsonPath("$.offerts[3].news").isArray)
                .andExpect(jsonPath("$.offerts[3].news[0].title").isString)
                .andExpect(jsonPath("$.offerts[3].news[0].description").isString)
                .andExpect(jsonPath("$.offerts[3].news[0].image").isString)

                .andExpect(jsonPath("$.others").exists())
                .andExpect(jsonPath("$.others.title").isString)
                .andExpect(jsonPath("$.others.description").isString)
                .andExpect(jsonPath("$.others.products").isArray)
                .andExpect(jsonPath("$.others.products[0].name").isString)
                .andExpect(jsonPath("$.others.products[0].image").isString)
                .andExpect(jsonPath("$.others.products[0].rating").isNumber)
                .andExpect(jsonPath("$.others.products[0].priceInTotal").isNumber)
                .andExpect(jsonPath("$.others.products[0].priceInChash").isNumber)
                .andExpect(jsonPath("$.others.products[0].priceOnTime").isString)
                .andExpect(jsonPath("$.others.products[0].favorite").isBoolean)
                .andExpect(jsonPath("$.others.products[0].discount").isNumber)
                .andExpect(jsonPath("$.others.pagination.page").isNumber)
                .andExpect(jsonPath("$.others.pagination.totalPage").isNumber)
                .andExpect(jsonPath("$.others.pagination.totalProduct").isNumber)
    }
}