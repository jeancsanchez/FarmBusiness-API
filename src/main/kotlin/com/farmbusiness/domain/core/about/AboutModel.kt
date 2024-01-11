package com.farmbusiness.domain.core.about

import javax.persistence.*

@Entity(name = "about")
data class AboutModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var about: String,
)