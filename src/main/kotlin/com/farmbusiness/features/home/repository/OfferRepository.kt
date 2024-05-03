package com.farmbusiness.features.home.repository


import com.farmbusiness.features.home.domain.offer.OfferModel
import org.springframework.data.repository.CrudRepository

interface OfferRepository : CrudRepository<OfferModel, Int?>