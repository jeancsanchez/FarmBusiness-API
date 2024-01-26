package com.farmbusiness.features.product.controller

import com.farmbusiness.features.product.controller.mappers.toModel
import com.farmbusiness.features.product.controller.request.CategoryRequest
import com.farmbusiness.features.product.domain.model.categories.CategoryModel
import com.farmbusiness.features.product.domain.service.CategoryService
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
@RequestMapping("/categories")
class CategoryController(
        private val categoryService: CategoryService
) {
    @PostMapping
    fun create(
            @Valid @RequestBody bodyRequest: CategoryRequest,
            request: HttpServletRequest,
    ): ResponseEntity<CategoryModel> {
        val entity = categoryService.create(categoryModel = bodyRequest.toModel())

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entity)
    }
}