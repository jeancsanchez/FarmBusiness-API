package com.farmbusiness.features.eula.repository

import com.farmbusiness.features.eula.domain.about.AboutModel
import org.springframework.data.jpa.repository.JpaRepository

interface AboutRepository : JpaRepository<AboutModel, Int> {
    fun findByAbout(about: AboutModel): List<AboutModel>

}