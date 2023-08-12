package com.farmbusiness.controller.model

import com.farmbusiness.enums.Role
import com.farmbusiness.enums.UsersStatus
import javax.persistence.*

@Entity(name = "users")
data class UsersModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var firstName: String? = null,

    @Column
    var cpf: String? = null,

    @Column
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
    @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])
    var roles: Set<Role> = setOf()
)