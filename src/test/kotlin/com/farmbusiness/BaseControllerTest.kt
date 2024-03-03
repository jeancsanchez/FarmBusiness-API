package com.farmbusiness

import com.farmbusiness.features.product.domain.model.ProductImageModel
import com.farmbusiness.features.product.domain.model.ProductModel
import com.farmbusiness.features.product.domain.model.categories.CategoryModel
import com.farmbusiness.features.product.domain.model.categories.SubCategoryModel
import com.farmbusiness.features.product.repository.ProductRepository
import com.farmbusiness.features.product.repository.categories.CategoryRepository
import com.farmbusiness.features.product.repository.categories.SubCategoryRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

@Suppress("SpellCheckingInspection")
@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner::class)
@TestExecutionListeners(DependencyInjectionTestExecutionListener::class)
class BaseControllerTest {

    @Autowired
    protected lateinit var context: WebApplicationContext

    protected lateinit var mvc: MockMvc

    protected lateinit var request: MockHttpServletRequestBuilder

    protected val mapper = ObjectMapper()

    @Autowired
    protected lateinit var productRepository: ProductRepository

    @Autowired
    protected lateinit var categoryRepository: CategoryRepository

    @Autowired
    protected lateinit var subCategoryRepository: SubCategoryRepository

    @BeforeEach
    fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @AfterEach
    fun after() {
        productRepository.deleteAll()
        subCategoryRepository.deleteAll()
        categoryRepository.deleteAll()
    }


    protected fun insertNewCategoryIntoDatabase(name: String): CategoryModel {
        return categoryRepository.save(CategoryModel(title = name))
    }

    protected fun insertNewSubCategoryIntoDatabase(name: String, categoryModel: CategoryModel): SubCategoryModel {
        return subCategoryRepository.save(SubCategoryModel(title = name, category = categoryModel))
    }

    protected fun insertNewProductIntoDatabase(name: String, subCategoryModel: SubCategoryModel): ProductModel {
        return productRepository.save(
                ProductModel(
                        title = name,
                        description = "some description",
                        presentation = "some presentation",
                        images = listOf(
                                ProductImageModel(imageUrl = "someurl")
                        ),
                        code = "somecode",
                        createdAt = Date(),
                        shelfLife = Date(),
                        batch = "FE1213435",
                        unitPrice = 3.4,
                        totalItems = 2,
                        subcategory = subCategoryModel
                )
        )
    }

}