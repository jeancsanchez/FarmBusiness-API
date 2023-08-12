package com.farmbusiness.extension

fun String.emptyCpf(): String = this.replace(".", "").replace("-", "")

fun String.emptyCnpj(): String = this.replace(".", "").replace("-", "").replace("/", "")