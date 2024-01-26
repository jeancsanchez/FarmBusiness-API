package com.farmbusiness.features.product.domain.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */

@Entity
@Table(name = "product_images")
class ProductImageModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    @NotBlank
    var imageUrl: String
)