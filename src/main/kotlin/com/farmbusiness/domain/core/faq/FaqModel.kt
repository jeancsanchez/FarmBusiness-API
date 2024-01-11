package com.farmbusiness.domain.core.faq

import javax.persistence.*

@Entity(name = "faq")
class FaqModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var title: String,

    @Column
    var description: String,
)