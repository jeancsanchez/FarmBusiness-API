package com.farmbusiness.features.product.repository

import com.farmbusiness.features.product.domain.model.ProductModel
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<ProductModel, Int?>