package com.farmbusiness.features.home.domain.offer

import com.farmbusiness.features.product.domain.model.ProductModel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "offers_product")
class OfferProductModel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @ManyToOne
        @JoinColumn(name = "product_id")
        var product: ProductModel,

        @Column
        var priceInTotal: Double,

        @Column
        var priceInCash: Double,

        @Column
        var priceOnTime: String? = null,

        @Column
        var discount: Double
)