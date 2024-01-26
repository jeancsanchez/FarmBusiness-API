package com.farmbusiness.features.product.repository.categories

import com.farmbusiness.features.product.domain.model.categories.SubCategoryModel
import org.springframework.data.repository.CrudRepository

interface SubCategoryRepository : CrudRepository<SubCategoryModel, Int?>