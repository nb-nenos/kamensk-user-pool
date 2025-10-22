package com.kamenskuserpool.dtos

import com.kamenskuserpool.configs.properties.AddressProperties
import com.kamenskuserpool.configs.properties.PhoneNumberProperties
import com.kamenskuserpool.enums.UserTypeEnum

data class UserDto(
    val fullName: String,
    val dateOfBirth: String,
    val cpf: String,
    val email: String,
    val password: String,
    val motherName: String,
    val address: List<AddressProperties>,
    val phoneNumber: List<PhoneNumberProperties>,
    val type: UserTypeEnum,
    val safetyPhrase: String
)
