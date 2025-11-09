package com.kamenskuserpool.utils

import com.kamenskuserpool.dtos.RequestSwitchAccountDto
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

    fun generateDtoException(): RequestSwitchAccountDto =
        RequestSwitchAccountDto(
            customerId = UUID.randomUUID().toString(),
            switchAccount = "batata"
        )
}
