package com.farmbusiness.features.eula.domain.about

import javax.persistence.*

@Entity(name = "about")
class AboutModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var about: String,
)