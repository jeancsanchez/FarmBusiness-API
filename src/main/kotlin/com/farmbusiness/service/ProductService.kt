package com.farmbusiness.service

import com.farmbusiness.controller.model.ProductImageModel
import com.farmbusiness.controller.model.product.ProductModel
import com.farmbusiness.repository.CategoryRepository
import com.farmbusiness.repository.ProductRepository
import com.farmbusiness.utils.ImageUtils
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.util.*

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) {

    fun create(
        product: ProductModel,
        images64: List<String>?,
        categoryId: Int,
        subCategoryId: Int,
        hostUrl: String,
    ): ProductModel {
        product.images = images64?.toImages(hostUrl)
        product.subcategory = categoryRepository.findById(categoryId)
            .orElseThrow()
            .subCategories
            .find { it.id == subCategoryId }

        return productRepository.save(product)
    }

    private fun List<String>.toImages(hostUrl: String): List<ProductImageModel> {
        if (isNotEmpty()) {
            return map { item ->
                ProductImageModel(
                    imageUrl = item
                        .decodeAndSaveImage()
                        .toImageUrl(hostUrl = hostUrl)
                )
            }
        }

        return emptyList()
    }

    private fun String.decodeAndSaveImage(): String {
        var base64 = this

        if (base64.contains("data:image")) {
            base64 = base64.split(",")[1]
        }

        return Base64.getDecoder()
            .decode(base64)
            .run {
                val now = Calendar.getInstance().timeInMillis
                val extension = "jpeg"
                val fileName = "$now.$extension"
                val path = "${ImageUtils.getImageBasePath()}/$fileName"
                val file = File(path)

                try {
                    val decoder = Base64.getDecoder()
                    val decodedBytes = decoder.decode(base64)

                    if (file.exists().not()) {
                        file.parentFile.mkdirs()
                    }

                    Files.write(
                        Path.of(file.path),
                        decodedBytes,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                fileName
            }
    }

    private fun String.toImageUrl(hostUrl: String): String {
        return "$hostUrl/${ImageUtils.getImageBaseUrl()}/$this"
    }
}