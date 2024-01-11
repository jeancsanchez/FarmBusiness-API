package com.farmbusiness.repository

import com.farmbusiness.domain.core.category.model.CategoryModel
import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<CategoryModel, Int?>