package com.farmbusiness.features.home.repository


import com.farmbusiness.features.home.domain.BannerModel
import org.springframework.data.repository.CrudRepository

interface BannerRepository : CrudRepository<BannerModel, Int>