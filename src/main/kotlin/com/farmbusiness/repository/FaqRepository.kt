package com.farmbusiness.repository

import com.farmbusiness.controller.model.FaqModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface FaqRepository : JpaRepository<FaqModel, Int>