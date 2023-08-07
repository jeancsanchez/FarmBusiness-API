package com.farmbusiness.controller.model

import javax.persistence.*

@Entity(name = "about")
data class AboutModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var about: String,
)