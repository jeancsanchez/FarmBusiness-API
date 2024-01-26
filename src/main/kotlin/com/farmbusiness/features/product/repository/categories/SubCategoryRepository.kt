package com.farmbusiness.repository

import com.farmbusiness.domain.core.category.model.SubCategoryModel
import org.springframework.data.repository.CrudRepository

interface SubCategoryRepository : CrudRepository<SubCategoryModel, Int?>