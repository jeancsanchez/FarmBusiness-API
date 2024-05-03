package com.farmbusiness.features.home.repository


import com.farmbusiness.features.home.domain.NewsModel
import org.springframework.data.repository.CrudRepository

interface NewsRepository : CrudRepository<NewsModel, Int>