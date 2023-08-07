package com.farmbusiness.service

import com.farmbusiness.controller.model.AboutModel
import com.farmbusiness.repository.AboutRepository
import org.springframework.stereotype.Service

@Service
class AboutService(
    private val aboutRepository: AboutRepository
) {

    fun create(about: AboutModel) {
        createNewOrUpdate(about)
    }

    fun findAbout(): AboutModel? {
        return aboutRepository.findAll().firstOrNull()
    }

    fun delete() {
        aboutRepository.deleteAll()
    }

    fun update(about: AboutModel) {
        createNewOrUpdate(about)
    }

    fun createNewOrUpdate(about: AboutModel) {
        aboutRepository.findAll().firstOrNull()?.let {
            about.id = it.id
        }
        aboutRepository.save(about)
    }
}
