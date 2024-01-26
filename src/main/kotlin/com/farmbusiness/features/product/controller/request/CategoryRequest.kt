package com.farmbusiness.features.product.controller.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */

data class CategoryRequest(
        @NotNull(message = "title is required") val title: String,

        @NotNull(message = "At least one subcategory is needed.")
        @NotEmpty(message = "At least one subcategory is needed.")
        val subCategories: List<SubCategoryRequest>
)


data class SubCategoryRequest(
        @NotNull(message = "title is required") val title: String
)