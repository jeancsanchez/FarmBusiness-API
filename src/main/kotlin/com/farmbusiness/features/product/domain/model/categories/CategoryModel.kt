package com.farmbusiness.features.product.domain.model.categories

import javax.persistence.*
import javax.validation.constraints.NotBlank

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */
@Entity
@Table(name = "categories")
class CategoryModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        @NotBlank
        var title: String,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn(name = "category_id")
        var subCategories: MutableList<SubCategoryModel> = mutableListOf(),
)