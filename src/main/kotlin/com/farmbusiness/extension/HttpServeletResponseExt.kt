package com.farmbusiness.extension

import com.farmbusiness.controller.response.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import javax.servlet.http.HttpServletResponse

/**
 * @author @jeancsanchez
 * @created 23/12/2023
 * Jesus loves you.
 */

fun HttpServletResponse.sendUnauthorized() {
    val status = HttpStatus.UNAUTHORIZED
    val errorResponse = ErrorResponse(
        httpCode = status.value(),
        message = status.reasonPhrase
    )

    this.status = status.value()
    this.contentType = MediaType.APPLICATION_JSON_VALUE
    writer.print(
        ObjectMapper().writeValueAsString(errorResponse)
    )
}