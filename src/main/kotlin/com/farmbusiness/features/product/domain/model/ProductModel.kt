package com.farmbusiness.features.product.domain.model

import com.farmbusiness.features.product.domain.model.categories.SubCategoryModel
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
@Entity
@Table(name = "products")
class ProductModel(

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
        var images: List<ProductImageModel>? = emptyList(),

        @ManyToOne
        @JoinColumn(name = "subcategory_id")
        var subcategory: SubCategoryModel?,

        @Column
        var code: String?,

        @Column
        var createdAt: Date? = Date(),

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