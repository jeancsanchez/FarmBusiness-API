package com.farmbusiness.controllers

import com.farmbusiness.BaseControllerTest
import com.farmbusiness.features.home.domain.BannerModel
import com.farmbusiness.features.home.repository.BannerRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

/**
 * @author @jeancsanchez
 * @created 18/12/2023ø
 * Jesus loves you.
 */
class HomeControllerTest : BaseControllerTest() {

    @Autowired
    lateinit var bannerRepository: BannerRepository


    @Test
    fun `GET Home Mobile com sucesso`() {
        // Given
        val category = insertNewCategoryIntoDatabase("Category 1")
        val subCategory = insertNewSubCategoryIntoDatabase("SubCategory 1", category)
        val product = insertNewProductIntoDatabase("Product 1", subCategory)
        val banner = bannerRepository.save(
                BannerModel(
                        image = "someImage",
                        deeplink = "someDeeplink",
                        route = "someRoute"
                )
        )


        // When
        request = get("/home/mobile")


        // Then
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().`is`(200))

                .andExpect(jsonPath("$.banners").isArray)
                .andExpect(jsonPath("$.banners[0].image").isString)
                .andExpect(jsonPath("$.banners[0].image").value(banner.image))
                .andExpect(jsonPath("$.banners[0].route").isString)
                .andExpect(jsonPath("$.banners[0].route").value(banner.route))
                .andExpect(jsonPath("$.banners[0].deeplink").isString)
                .andExpect(jsonPath("$.banners[0].deeplink").value(banner.deeplink))

                .andExpect(jsonPath("$.offers").isArray)
                .andExpect(jsonPath("$.offers[0].id").isNumber)
                .andExpect(jsonPath("$.offers[0].title").isString)
                .andExpect(jsonPath("$.offers[0].description").isString)
                .andExpect(jsonPath("$.offers[0].type").isString)
                .andExpect(jsonPath("$.offers[0].type").value("OFFER_PRODUCT"))
                .andExpect(jsonPath("$.offers[0].products").isArray)
                .andExpect(jsonPath("$.offers[0].products[0].name").isString)
                .andExpect(jsonPath("$.offers[0].products[0].image").isString)
                .andExpect(jsonPath("$.offers[0].products[0].rating").isNumber)
                .andExpect(jsonPath("$.offers[0].products[0].priceInTotal").isNumber)
                .andExpect(jsonPath("$.offers[0].products[0].priceInChash").isNumber)
                .andExpect(jsonPath("$.offers[0].products[0].priceOnTime").isString)
                .andExpect(jsonPath("$.offers[0].products[0].favorite").isBoolean)
                .andExpect(jsonPath("$.offers[0].products[0].discount").isNumber)

                .andExpect(jsonPath("$.offers[1].id").isNumber)
                .andExpect(jsonPath("$.offers[1].title").isString)
                .andExpect(jsonPath("$.offers[1].description").isString)
                .andExpect(jsonPath("$.offers[1].type").isString)
                .andExpect(jsonPath("$.offers[1].type").value("OFFER_SEEDS"))
                .andExpect(jsonPath("$.offers[1].category").isArray)
                .andExpect(jsonPath("$.offers[1].category[0].id").isNumber)
                .andExpect(jsonPath("$.offers[1].category[0].title").isString)
                .andExpect(jsonPath("$.offers[1].category[0].image").isNumber)

                .andExpect(jsonPath("$.offers[2].id").isNumber)
                .andExpect(jsonPath("$.offers[2].title").isString)
                .andExpect(jsonPath("$.offers[2].description").isString)
                .andExpect(jsonPath("$.offers[2].type").isString)
                .andExpect(jsonPath("$.offers[2].type").value("OFFER_MOBILE"))
                .andExpect(jsonPath("$.offers[2].iosRedirect").doesNotExist())
                .andExpect(jsonPath("$.offers[2].androidRedirect").isString)

                .andExpect(jsonPath("$.offers[3].id").isNumber)
                .andExpect(jsonPath("$.offers[3].title").isString)
                .andExpect(jsonPath("$.offers[3].description").isString)
                .andExpect(jsonPath("$.offers[3].type").isString)
                .andExpect(jsonPath("$.offers[3].type").value("OFFER_NEWS"))
                .andExpect(jsonPath("$.offers[3].news").isArray)
                .andExpect(jsonPath("$.offers[3].news[0].title").isString)
                .andExpect(jsonPath("$.offers[3].news[0].description").isString)
                .andExpect(jsonPath("$.offers[3].news[0].image").isString)

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