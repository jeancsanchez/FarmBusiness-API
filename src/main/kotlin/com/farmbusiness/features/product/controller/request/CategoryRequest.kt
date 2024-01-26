package com.farmbusiness.features.product.controller.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */

data class CategoryRequest(
        @field:NotNull(message = "title is required") val title: String,

        @field:NotEmpty(message = "At least one subcategory is needed.")
        val subCategories: List<SubCategoryRequest>
)


data class SubCategoryRequest(
        @field:NotNull(message = "title is required") val title: String
)