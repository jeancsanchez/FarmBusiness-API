package com.farmbusiness.controller.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */
@Suppress("SpellCheckingInspection")
@Entity(name = "products")
data class ProductModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    @NotBlank
    var title: String,

    @Column
    var description: String,

    @Column
    var presentation: String,

    @Column
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "product_id")
    var images: List<ProductImageModel>?,

    // TODO
//    @Column
//    var freight: String,


    // TODO
//    @Column
//    var dicount: String,

    @Column
    var code: String?,

    @Column
    var createdAt: Date,

    @Column
    @NotBlank
    var shelfLife: Date,

    @Column
    @NotBlank
    var batch: String,

    @Column
    var unitPrice: Double,

    @Column
    @Min(value = 1, message = "Pelo menos 1 item deve ser cadastrado")
    var totalItems: Int
)