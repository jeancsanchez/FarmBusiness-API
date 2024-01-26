package com.farmbusiness.config

import com.farmbusiness.config.security.AuthorizationFilter
import com.farmbusiness.config.security.JwtUtil
import com.farmbusiness.features.user.domain.model.Role
import com.farmbusiness.features.user.domain.service.UserDetailsCustomService
import com.farmbusiness.features.user.repository.UsersRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
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
) : WebSecurityConfigurerAdapter() {

    private val publicPostMatchers = arrayOf(
        "/users",
        "/login"
    )

    private val adminMatchers = arrayOf(
        "/admin/**"
    )

    private val swaggerMatchers = arrayOf(
        "/v2/api-docs", "/v3/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security",
        "/swagger-ui/**", "/webjars/**", "/csrf/**"
    )

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()

        http.authorizeRequests()
            .antMatchers(*adminMatchers).hasAuthority(Role.ADMIN.description)
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(AuthorizationFilter(authenticationManager(), userDetails, jwtUtil))
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers(*swaggerMatchers)
            .antMatchers(HttpMethod.POST, *publicPostMatchers)
            .antMatchers("/static/**")
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
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