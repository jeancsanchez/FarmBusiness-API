package com.farmbusiness.features.product.controller.mappers

import com.farmbusiness.features.product.controller.request.CategoryRequest
import com.farmbusiness.features.product.domain.model.categories.CategoryModel
import com.farmbusiness.features.product.domain.model.categories.SubCategoryModel

/**
 * @author @jeancsanchez
 * @created 09/01/2024
 * Jesus loves you.
 */

fun CategoryRequest.toModel(): CategoryModel =
        CategoryModel(
                title = title,
                subCategories = subCategories
                        .map { SubCategoryModel(title = it.title, category = null) }
                        .toMutableList()
        )