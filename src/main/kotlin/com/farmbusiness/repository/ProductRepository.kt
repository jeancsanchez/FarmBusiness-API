package com.farmbusiness.repository

import com.farmbusiness.controller.model.ProductModel
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<ProductModel, Int?>