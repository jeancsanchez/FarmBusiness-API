package com.farmbusiness.features.user.domain.model

import javax.persistence.*
import javax.validation.constraints.Email

@Suppress("SpellCheckingInspection")
@Entity(name = "users")
class UsersModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        var firstName: String? = null,

        @Column
        var cpf: String? = null,

        @Column
        @Email(message = "Email inválido")
        var email: String,

        @Column
        var password: String,

        @Column
        var company: String? = null,

        @Column
        var fantasyName: String? = null,

        @Column
        var cnpj: String? = null,

        @Column
        @Enumerated(EnumType.STRING)
        var status: UsersStatus? = null,

        @Column
        var phone: String? = null,

        @Column(name = "role")
        @Enumerated(EnumType.STRING)
        @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
        @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
        var roles: Set<Role> = setOf()
)