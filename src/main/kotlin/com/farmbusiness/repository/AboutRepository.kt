package com.farmbusiness.repository

import com.farmbusiness.controller.model.AboutModel
import org.springframework.data.jpa.repository.JpaRepository

interface AboutRepository : JpaRepository<AboutModel, Int> {
    fun findByAbout(about: AboutModel): List<AboutModel>

}