package com.farmbusiness.features.eula.domain.terms

import javax.persistence.*

@Entity(name = "terms_use")
class TermsOfUseModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var termsOfUse: String,
)