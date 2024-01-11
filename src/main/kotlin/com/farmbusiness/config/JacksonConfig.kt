package com.farmbusiness.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder


/**
 * @author @jeancsanchez
 * @created 11/01/2024
 * Jesus loves you.
 */

@Configuration
class JacksonConfig {

    @Bean
    fun jackson2ObjectMapperBuilder(): Jackson2ObjectMapperBuilder {
        val builder = Jackson2ObjectMapperBuilder()
        return builder
            .serializationInclusion(JsonInclude.Include.NON_NULL) // Hide null values
            .serializationInclusion(JsonInclude.Include.NON_EMPTY) // Hide empty values
            .failOnEmptyBeans(true)
            .failOnUnknownProperties(true)
    }

    @Bean
    fun objectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
        return builder.build()
    }
}