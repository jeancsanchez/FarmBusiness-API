package com.farmbusiness.features.product.repository.categories

import com.farmbusiness.features.product.domain.model.categories.CategoryModel
import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<CategoryModel, Int?>