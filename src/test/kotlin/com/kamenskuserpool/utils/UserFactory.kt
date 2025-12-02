package com.kamenskuserpool.utils

import com.kamenskuserpool.configs.properties.AddressProperties
import com.kamenskuserpool.configs.properties.PhoneNumberProperties
import com.kamenskuserpool.dtos.UserDto
import com.kamenskuserpool.enums.UserTypeEnum
import com.kamenskuserpool.models.UserModel
import java.util.*

object UserFactory {

    fun generateUserAccountOn(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Maria",
            safepayUserId = 2,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "12345678910"
        )

    fun generateUserAccountOff(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Ana",
            safepayUserId = 3,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "09876543212",
        )

    fun generateUserAccountException(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Carol",
            safepayUserId = 4,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "90876512354"
        )

    fun generateUserCreditOn(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            creditFlg = false
        )

    fun generateUserCreditOff(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            creditFlg = true
        )

    fun generateUserCreditException(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            creditFlg = false
        )

    fun generateUserPrepaidOn(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            prepaidFlg = false
        )

    fun generateUserPrepaidOff(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            prepaidFlg = true
        )

    fun generateUserPrepaidException(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            prepaidFlg = false
        )

    fun generateSafepayUserId(): UserDto =
        UserDto(
            fullName = "Bruna",
            dateOfBirth = "28.05.2025",
            cpf = "12345678990",
            email = "bruna@email.com",
            password = "2805",
            motherName = "Mae",
            address = listOf(
                AddressProperties(
                streetName = "Rua das Flores",
                district = "São Judas",
                number = "213",
                complement = "apt 80",
                postalCode = "4380817",
                state = "São Paulo",
                city = "São Paulo"
                )
            ),
            phoneNumber = listOf(
                PhoneNumberProperties(
                    countryCode = "55",
                    areaCode = "11",
                    number = "983264562"
                )
            ),
            type = UserTypeEnum.PF,
            safetyPhrase = "pergunta aqui"

        )
}
