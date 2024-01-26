package com.farmbusiness.features.product.domain.service

import com.farmbusiness.features.product.domain.model.categories.CategoryModel
import com.farmbusiness.features.product.repository.categories.CategoryRepository
import org.springframework.stereotype.Service

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository
) {
    fun create(categoryModel: CategoryModel): CategoryModel {
        return categoryRepository.save(categoryModel)
    }
}