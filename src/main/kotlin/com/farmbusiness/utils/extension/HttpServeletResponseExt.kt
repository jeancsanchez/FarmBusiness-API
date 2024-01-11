package com.farmbusiness.utils.extension

import com.farmbusiness.domain.errors.exceptions.AuthenticationException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import javax.servlet.http.HttpServletResponse

/**
 * @author @jeancsanchez
 * @created 23/12/2023
 * Jesus loves you.
 */

fun HttpServletResponse.sendUnauthorized(
    exception: AuthenticationException = AuthenticationException()
) {
    val response = exception.buildErrorResponse()

    status = response.httpCode
    contentType = MediaType.APPLICATION_JSON_VALUE
    writer.print(
        ObjectMapper().writeValueAsString(response)
    )
}