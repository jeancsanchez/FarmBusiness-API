package com.farmbusiness.features.home.controller.response

import com.farmbusiness.features.home.domain.BannerModel

data class HomeMobileResponse(
        val banners: List<BannerModel> = emptyList()
)