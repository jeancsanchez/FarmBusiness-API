package com.farmbusiness.controller

import com.farmbusiness.controller.mappers.toModel
import com.farmbusiness.controller.mappers.toResponse
import com.farmbusiness.controller.request.product.ProductRequest
import com.farmbusiness.controller.response.ProductResponse
import com.farmbusiness.domain.core.product.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
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
    fun create(
        @Valid @RequestBody bodyRequest: ProductRequest,
        request: HttpServletRequest,
    ): ResponseEntity<ProductResponse> {
        val entity = productService.create(
            product = bodyRequest.toModel(),
            images64 = bodyRequest.images,
            categoryId = bodyRequest.categoryId,
            subCategoryId = bodyRequest.subCategoryId,
            hostUrl = request
                .requestURL
                .buildBaseUrl(request)
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(entity.toResponse())

    }

    private fun StringBuffer.buildBaseUrl(request: HttpServletRequest): String {
        val string = toString()
        val protocol = string.split("://")[0]
        val builder = StringBuilder()

        return builder
            .append(protocol)
            .append("://")
            .append(request.serverName)
            .append(":")
            .append(request.serverPort)
            .toString()
    }
}