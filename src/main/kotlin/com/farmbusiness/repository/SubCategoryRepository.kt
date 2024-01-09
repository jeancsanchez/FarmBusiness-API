package com.farmbusiness.repository

import com.farmbusiness.controller.model.product.SubCategoryModel
import org.springframework.data.repository.CrudRepository

interface SubCategoryRepository : CrudRepository<SubCategoryModel, Int?>