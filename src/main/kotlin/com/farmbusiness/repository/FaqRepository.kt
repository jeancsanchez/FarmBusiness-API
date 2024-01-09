package com.farmbusiness.repository

import com.farmbusiness.controller.model.FaqModel
import org.springframework.data.jpa.repository.JpaRepository

interface FaqRepository : JpaRepository<FaqModel, Int>