package com.farmbusiness.repository

import com.farmbusiness.domain.core.product.model.ProductModel
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<ProductModel, Int?>