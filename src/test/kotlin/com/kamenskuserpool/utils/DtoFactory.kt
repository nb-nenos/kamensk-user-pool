package com.kamenskuserpool.utils

import com.kamenskuserpool.configs.properties.AddressProperties
import com.kamenskuserpool.configs.properties.PhoneNumberProperties
import com.kamenskuserpool.dtos.RequestSwitchAccountDto
import com.kamenskuserpool.dtos.RequestSwitchCreditDto
import com.kamenskuserpool.dtos.RequestSwitchPrepaidDto
import com.kamenskuserpool.dtos.UserDto
import com.kamenskuserpool.enums.UserTypeEnum
import java.util.UUID

object DtoFactory {

    fun generateDtoAccountOn(): RequestSwitchAccountDto =
        RequestSwitchAccountDto(
            customerId = UUID.randomUUID().toString(),
            switchAccount = "on"
        )

    fun generateDtoAccountOff(): RequestSwitchAccountDto =
        RequestSwitchAccountDto(
            customerId = UUID.randomUUID().toString(),
            switchAccount = "off"
        )

    fun generateDtoAccountException(): RequestSwitchAccountDto =
        RequestSwitchAccountDto(
            customerId = UUID.randomUUID().toString(),
            switchAccount = "batata"
        )

    fun generateDtoCreditOn(): RequestSwitchCreditDto =
        RequestSwitchCreditDto(
            customerId = UUID.randomUUID().toString(),
            switchCredit = "on"
        )

    fun generateDtoCreditOff(): RequestSwitchCreditDto =
        RequestSwitchCreditDto(
            customerId = UUID.randomUUID().toString(),
            switchCredit = "off"
        )

    fun generateDtoCreditException(): RequestSwitchCreditDto =
        RequestSwitchCreditDto(
            customerId = UUID.randomUUID().toString(),
            switchCredit = ".."
        )

    fun generateDtoPrepaidOn(): RequestSwitchPrepaidDto =
        RequestSwitchPrepaidDto(
            customerId = UUID.randomUUID().toString(),
            switchPrepaid = "on"
        )

    fun generateDtoPrepaidOff(): RequestSwitchPrepaidDto =
        RequestSwitchPrepaidDto(
            customerId = UUID.randomUUID().toString(),
            switchPrepaid = "off"
        )

    fun generateDtoPrepaidException(): RequestSwitchPrepaidDto =
        RequestSwitchPrepaidDto(
            customerId = UUID.randomUUID().toString(),
            switchPrepaid = "..."
        )

    fun generateDtoCustomerAPI(): UserDto =
        UserDto(
            fullName = "Anna Souza",
            dateOfBirth = "28/05/2025",
            cpf = "678234562191",
            email = "anna@email.com",
            password = "1234",
            motherName = "Clara",
            address = listOf(
                AddressProperties(
                    streetName = "Rua das Flores",
                    district = "Vila Mariana",
                    number = "123",
                    complement = "Apto 1",
                    postalCode = "89011-546",
                    state = "SP",
                    city = "Sao Paulo"
                )
            ),
            phoneNumber = listOf(
                PhoneNumberProperties(
                    countryCode = "+55",
                    areaCode = "11",
                    number = "983216723"
                )
            ),
            type = UserTypeEnum.PF,
            safetyPhrase = "Qual a sua fruta favorita?"
        )
}
