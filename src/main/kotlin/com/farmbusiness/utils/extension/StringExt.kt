package com.farmbusiness.utils.extension

fun String.emptyCpf(): String = this.replace(".", "").replace("-", "")

fun String.emptyCnpj(): String = this.replace(".", "").replace("-", "").replace("/", "")