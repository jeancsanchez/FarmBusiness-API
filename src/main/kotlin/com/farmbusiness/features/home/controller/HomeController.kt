package com.farmbusiness.features.home.controller

import com.farmbusiness.features.home.controller.response.HomeMobileResponse
import com.farmbusiness.features.home.domain.BannerModel
import com.farmbusiness.features.user.domain.service.LoginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author @jeancsanchez
 * @created 23/12/2023
 * Jesus loves you.
 */

@RestController
@RequestMapping
class HomeController(
        private val loginService: LoginService
) {
    @GetMapping("/home/mobile")
    fun home(): ResponseEntity<HomeMobileResponse> {
        val data = HomeMobileResponse(
                banners = listOf(
                        BannerModel(
                                image = "someImage",
                                route = "someRoute",
                                deeplink = "someDeeplink"
                        )
                )
        )

        return ResponseEntity.ok(data)
    }
}