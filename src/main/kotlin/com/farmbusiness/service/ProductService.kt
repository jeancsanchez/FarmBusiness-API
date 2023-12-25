package com.farmbusiness.service

import com.farmbusiness.controller.model.ProductModel
import com.farmbusiness.repository.ProductRepository
import org.springframework.stereotype.Service

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun create(product: ProductModel) {
        productRepository.save(product)
    }
}