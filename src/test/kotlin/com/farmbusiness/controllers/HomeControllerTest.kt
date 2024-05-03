package com.farmbusiness.controllers

import com.farmbusiness.BaseControllerTest
import com.farmbusiness.features.home.domain.BannerModel
import com.farmbusiness.features.home.domain.NewsModel
import com.farmbusiness.features.home.domain.offer.OfferModel
import com.farmbusiness.features.home.domain.offer.OfferProductModel
import com.farmbusiness.features.home.repository.BannerRepository
import com.farmbusiness.features.home.repository.NewsRepository
import com.farmbusiness.features.home.repository.OfferProductRepository
import com.farmbusiness.features.home.repository.OfferRepository
import com.farmbusiness.features.product.domain.model.ProductImageModel
import com.farmbusiness.features.product.domain.model.ProductModel
import com.farmbusiness.features.product.domain.model.categories.CategoryModel
import com.farmbusiness.features.product.domain.model.categories.SubCategoryModel
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import java.util.*

/**
 * @author @jeancsanchez
 * @created 18/12/2023ø
 * Jesus loves you.
 */
class HomeControllerTest : BaseControllerTest() {

    @Autowired
    lateinit var bannerRepository: BannerRepository

    @Autowired
    lateinit var offerRepository: OfferRepository

    @Autowired
    lateinit var offerProductRepository: OfferProductRepository

    @Autowired
    lateinit var newsRepository: NewsRepository


    @Test
    fun `GET Home Mobile com sucesso`() {
        // Given
        val banner = bannerRepository.save(
                BannerModel(
                        image = "someImage",
                        deeplink = "someDeeplink",
                        route = "someRoute"
                )
        )

        val mockCategory = categoryRepository.save(CategoryModel(3, "someCategory"))
        val mockSubcatgory = subCategoryRepository.save(SubCategoryModel(3, "someSubCategory", mockCategory))

        val product = insertNewProductIntoDatabase("Product 1")
        val productOffer = offerProductRepository.save(
                OfferProductModel(
                        product =  productRepository.save(
                                ProductModel(
                                        title = "some",
                                        description = "some description",
                                        presentation = "some presentation",
                                        rating = 3.2f,
                                        images = listOf(
                                                ProductImageModel(imageUrl = "someurl")
                                        ),
                                        code = "somecode",
                                        createdAt = Date(),
                                        shelfLife = Date(),
                                        batch = "FE1213435",
                                        unitPrice = 3.4,
                                        totalItems = 2,
                                        subcategory = mockSubcatgory
                                )
                        ),
                        priceInTotal = 2.0,
                        priceInCash = 3.0,
                        priceOnTime = "Discount of R$ 3.2 in debit",
                        discount = 10.0
                )
        )

        val news = newsRepository.save(
                NewsModel(
                        title = "Some news",
                        description = "Some news description",
                        image = "someImage"
                )
        )

        val offer = offerRepository.save(
                OfferModel(
                        title = "Offer 1",
                        description = "Offer description",
                        products = listOf(productOffer),
                        type = "OFFER_NEWS",
                        categories = listOf(),
                        news = listOf(),
                        iosRedirect = "someIosUrl",
                        androidRedirect = "someAndroidUrl"
                )
        )


        // When
        request = get("/home/mobile")


        // Then
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().`is`(200))

                .andExpect(jsonPath("$.banners").isArray)
                .andExpect(jsonPath("$.banners[0].image").value(banner.image))
                .andExpect(jsonPath("$.banners[0].route").value(banner.route))
                .andExpect(jsonPath("$.banners[0].deeplink").value(banner.deeplink))

                .andExpect(jsonPath("$.offers").isArray)
                .andExpect(jsonPath("$.offers[0].id").value(offer.id))
                .andExpect(jsonPath("$.offers[0].title").value(offer.title))
                .andExpect(jsonPath("$.offers[0].description").value(offer.description))
                .andExpect(jsonPath("$.offers[0].type").value(offer.type))
                .andExpect(jsonPath("$.offers[0].products[0].name").value(productOffer.product.title))
                .andExpect(jsonPath("$.offers[0].products[0].image").value(productOffer.product.images?.first()))
                .andExpect(jsonPath("$.offers[0].products[0].rating").value(productOffer.product.rating))
                .andExpect(jsonPath("$.offers[0].products[0].priceInTotal").value(productOffer.priceInTotal))
                .andExpect(jsonPath("$.offers[0].products[0].priceInCash").value(productOffer.priceInCash))
                .andExpect(jsonPath("$.offers[0].products[0].priceOnTime").value(productOffer.priceOnTime))
//                .andExpect(jsonPath("$.offers[0].products[0].favorite").value(productOffer.favorite))
                .andExpect(jsonPath("$.offers[0].products[0].discount").value(productOffer.discount))

                .andExpect(jsonPath("$.offers[1].categories").isArray)
                .andExpect(jsonPath("$.offers[1].categories[0].id").isNumber)
                .andExpect(jsonPath("$.offers[1].categories[0].title").isString)
                .andExpect(jsonPath("$.offers[1].categories[0].image").isNumber)

                .andExpect(jsonPath("$.offers[2].iosRedirect").doesNotExist())
                .andExpect(jsonPath("$.offers[2].androidRedirect").isString)

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