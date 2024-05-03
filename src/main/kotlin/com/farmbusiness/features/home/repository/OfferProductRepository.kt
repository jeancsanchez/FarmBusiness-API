package com.farmbusiness.features.home.repository


import com.farmbusiness.features.home.domain.offer.OfferProductModel
import org.springframework.data.repository.CrudRepository

interface OfferProductRepository : CrudRepository<OfferProductModel, Int?>