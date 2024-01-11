package com.farmbusiness.repository

import com.farmbusiness.domain.core.faq.FaqModel
import org.springframework.data.jpa.repository.JpaRepository

interface FaqRepository : JpaRepository<FaqModel, Int>