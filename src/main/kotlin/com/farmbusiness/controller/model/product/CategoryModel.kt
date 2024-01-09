package com.farmbusiness.controller.model.product

import javax.persistence.*
import javax.validation.constraints.NotBlank

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */
@Entity
@Table(name = "categories")
class CategoryModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    @NotBlank
    var title: String,

    @Column
    @OneToMany
    @JoinColumn(name = "category_id")
    var subCategories: List<SubCategoryModel>? = emptyList(),
)