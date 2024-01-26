package com.farmbusiness.features.product.domain.model.categories

import com.farmbusiness.features.product.domain.model.ProductModel
import javax.persistence.*
import javax.validation.constraints.NotBlank

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */
@Entity
@Table(name = "subcategories")
class SubCategoryModel(

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

        @Column
    @NotBlank
    var title: String,

        @ManyToOne
    @JoinColumn(name = "category_id")
    var category: CategoryModel,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "subcategory_id")
    val products: MutableList<ProductModel> = mutableListOf()
)