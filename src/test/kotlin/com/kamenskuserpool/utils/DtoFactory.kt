package com.kamenskuserpool.utils

import com.kamenskuserpool.dtos.RequestSwitchAccountDto
import com.kamenskuserpool.dtos.RequestSwitchCreditDto
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
            switchCredit = ""
        )
}
