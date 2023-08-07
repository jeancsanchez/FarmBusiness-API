package com.farmbusiness.controller.model

import javax.persistence.*

@Entity(name = "faq")
data class FaqModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var title: String,

    @Column
    var description: String,
)