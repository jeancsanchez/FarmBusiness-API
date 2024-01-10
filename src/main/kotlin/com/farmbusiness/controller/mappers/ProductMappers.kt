package com.farmbusiness.controller.mappers

import com.farmbusiness.controller.model.product.ProductModel
import com.farmbusiness.controller.request.product.ProductRequest
import com.farmbusiness.controller.response.ProductResponse
import com.farmbusiness.extension.toDate

/**
 * @author @jeancsanchez
 * @created 09/01/2024
 * Jesus loves you.
 */

fun ProductRequest.toModel(): ProductModel =
    ProductModel(
        id = id,
        title = title,
        description = description,
        presentation = presentation,
        code = code,
        shelfLife = shelfLifeMillis.toDate(),
        batch = batch,
        unitPrice = unitPrice,
        totalItems = totalItems,
        subcategory = null
    )

fun ProductModel.toResponse(): ProductResponse =
    ProductResponse(
        id = id!!,
        title = title,
        description = description,
        presentation = presentation,
        code = code,
        images = images?.map { it.imageUrl } ?: emptyList(),
        batch = batch,
        shelfLifeMillis = shelfLife.time,
        unitPrice = unitPrice,
        totalItems = totalItems
    )