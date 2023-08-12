package com.farmbusiness.extension

import com.farmbusiness.controller.model.*
import com.farmbusiness.controller.request.*
import com.farmbusiness.controller.response.*
import com.farmbusiness.enums.Role
import org.springframework.data.domain.Page

fun PostAboutRequest.toAboutModel(): AboutModel {
    return AboutModel(
        about = this.about
    )
}

fun PutAboutRequest.toAboutModel(): AboutModel {
    return AboutModel(
        about = this.about
    )
}

fun PostPrivacyPolicyRequest.toPrivacyPolicyModel(): PrivacyPolicyModel {
    return PrivacyPolicyModel(
        privacyPolicy = this.privacyPolicy
    )
}

fun PostUsersRequest.toUsersModel(): UsersModel {
    return UsersModel(
        firstName = this.firstName,
        cpf = this.cpf,
        email = this.email,
        password = this.password,
        company = this.company,
        fantasyName = this.fantasyName,
        cnpj = this.cnpj,
        phone = this.phone,
        roles = setOf(if(this.type == "buyer") Role.BUYER else Role.SELLER)
    )
}

fun PutPrivacyPolicyRequest.toPrivacyPolicyModel(): PrivacyPolicyModel {
    return PrivacyPolicyModel(
        privacyPolicy = this.privacyPolicy
    )
}

fun PostTermsOfUseRequest.toTermsOfUseModel(): TermsOfUseModel {
    return TermsOfUseModel(
        termsOfUse = this.termsOfUse
    )
}

fun PutTermsOfUseRequest.toTermsOfUseModel(): TermsOfUseModel {
    return TermsOfUseModel(
        termsOfUse = this.termsOfUse
    )
}

fun PostFaqRequest.toFaqModel(): FaqModel {
    return FaqModel(
        title = this.title,
        description = this.description
    )
}

fun PutFaqRequest.toFaqModel(id: Int): FaqModel {
    return FaqModel(
        id = id,
        title = this.title,
        description = this.description
    )
}

fun AboutModel.toResponse(): AboutResponse {
    return AboutResponse(
        id = this.id,
        about = this.about
    )
}

fun PrivacyPolicyModel.toResponse(): PrivacyPolicyResponse {
    return PrivacyPolicyResponse(
        id = this.id,
        privacyPolicy = this.privacyPolicy
    )
}

fun TermsOfUseModel.toResponse(): TermsOfUseResponse {
    return TermsOfUseResponse(
        id = this.id,
        termsOfUse = this.termsOfUse
    )
}

fun FaqModel.toResponse(): FaqResponse {
    return FaqResponse(
        id = this.id,
        title = this.title,
        description = this.description
    )
}

fun <T> Page<T>.toPageResponse(): PageResponse<T> {
    return PageResponse(
        this.content,
        this.number,
        this.totalElements,
        this.totalPages
    )
}
