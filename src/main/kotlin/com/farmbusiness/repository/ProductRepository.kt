package com.farmbusiness.repository

import com.farmbusiness.controller.model.product.ProductModel
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<ProductModel, Int?>