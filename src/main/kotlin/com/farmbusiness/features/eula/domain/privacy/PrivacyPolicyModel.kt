package com.farmbusiness.features.eula.domain.privacy

import javax.persistence.*

@Entity(name = "privacy_policy")
class PrivacyPolicyModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        var privacyPolicy: String,
)