package com.farmbusiness.repository

import com.farmbusiness.domain.core.about.AboutModel
import org.springframework.data.jpa.repository.JpaRepository

interface AboutRepository : JpaRepository<AboutModel, Int> {
    fun findByAbout(about: AboutModel): List<AboutModel>

}