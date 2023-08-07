package com.farmbusiness.controller.model

import javax.persistence.*

@Entity(name = "terms_use")
data class TermsOfUseModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var termsOfUse: String,
)