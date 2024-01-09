package com.farmbusiness.repository

import com.farmbusiness.controller.model.product.CategoryModel
import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<CategoryModel, Int?>