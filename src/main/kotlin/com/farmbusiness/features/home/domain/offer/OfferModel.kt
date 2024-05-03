package com.farmbusiness.features.home.domain.offer

import com.farmbusiness.features.home.domain.NewsModel
import com.farmbusiness.features.product.domain.model.categories.CategoryModel
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "offers")
class OfferModel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        @NotBlank
        var title: String = "",

        @Column
        var description: String? = null,

        @Column
        @NotBlank
        var type: String = "",

        @Column
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = false)
        @JoinColumn(name = "offers_product_id")
        var products: List<OfferProductModel>? = emptyList(),

        @Column
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = false)
        @JoinColumn(name = "offer_id")
        var categories: List<CategoryModel>? = emptyList(),

        @Column
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = false)
        @JoinColumn(name = "offer_id")
        var news: List<NewsModel>? = emptyList(),

        @Column
        var iosRedirect: String? = null,

        @Column
        var androidRedirect: String? = null
)