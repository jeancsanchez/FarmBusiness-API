package com.farmbusiness.controller.model

import javax.persistence.*

@Entity(name = "privacy_policy")
data class PrivacyPolicyModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var privacyPolicy: String,
)