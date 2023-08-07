package com.farmbusiness.controller.exception

class NotFoundException(override val message: String, val errorCode: String) : Exception() {
}