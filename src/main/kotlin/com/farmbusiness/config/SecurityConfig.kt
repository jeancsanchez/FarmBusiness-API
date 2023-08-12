package com.farmbusiness.config

import com.farmbusiness.config.security.AuthenticationFilter
import com.farmbusiness.config.security.AuthorizationFilter
import com.farmbusiness.config.security.JwtUtil
import com.farmbusiness.enums.Role
import com.farmbusiness.repository.UsersRepository
import com.farmbusiness.service.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val usersRepository: UsersRepository,
    private val userDetails: UserDetailsCustomService,
    private val jwtUtil: JwtUtil
): WebSecurityConfigurerAdapter() {

    private val publicPostMatchers = arrayOf(
        "/users"
    )

    private val adminMatchers = arrayOf(
        "/admin/**"
    )

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()

        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, *publicPostMatchers).permitAll()
            .antMatchers(*adminMatchers).hasAuthority(Role.ADMIN.description)
            .anyRequest().authenticated()
        http.addFilter(AuthenticationFilter(authenticationManager(), usersRepository, jwtUtil))
        http.addFilter(AuthorizationFilter(authenticationManager(), userDetails, jwtUtil))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/v2/api-docs", "/v3/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security",
                "/swagger-ui/**", "/webjars/**", "/csrf/**")
    }

    @Bean
    fun corsConfig(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}