package com.farmbusiness.features.home.controller.response

import com.farmbusiness.features.home.domain.BannerModel
import com.farmbusiness.features.home.domain.offer.OfferModel

data class HomeMobileResponse(
        val banners: List<BannerModel> = emptyList(),
        val offers: List<OfferModel> = emptyList(),
)