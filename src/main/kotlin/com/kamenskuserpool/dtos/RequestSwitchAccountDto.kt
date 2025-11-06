package com.kamenskuserpool.dtos

import java.util.UUID

data class RequestSwitchAccountDto(
    val customerId: String,
    val switchAccount: String
)
