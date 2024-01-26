package com.farmbusiness.features.eula.repository

import com.farmbusiness.features.eula.domain.faq.FaqModel
import org.springframework.data.jpa.repository.JpaRepository

interface FaqRepository : JpaRepository<FaqModel, Int>