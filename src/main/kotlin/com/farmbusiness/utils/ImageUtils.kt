package com.farmbusiness.utils

import org.springframework.core.io.ClassPathResource

/**
 * @author @jeancsanchez
 * @created 09/01/2024
 * Jesus loves you.
 */

object ImageUtils {

    private const val SUB_FOLDER = "images"

    fun getImageBaseUrl(): String = "static/$SUB_FOLDER"

    fun getImageBasePath(): String = "${ClassPathResource("static").getFile().absolutePath}/$SUB_FOLDER"
}