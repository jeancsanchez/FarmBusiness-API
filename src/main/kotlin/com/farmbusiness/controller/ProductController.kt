package com.farmbusiness.controller

import com.farmbusiness.controller.request.product.ProductRequest
import com.farmbusiness.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {
    @PostMapping
    fun create(@RequestBody @Valid request: ProductRequest): ResponseEntity<Unit> {
        return try {
            productService.create(request.toModel())
            ResponseEntity
                .status(HttpStatus.CREATED)
                .build()

        } catch (t: Throwable) {
            t.printStackTrace()
            ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build()
        }
    }
}