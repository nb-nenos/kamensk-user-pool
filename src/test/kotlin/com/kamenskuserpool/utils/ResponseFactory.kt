package com.kamenskuserpool.utils

import com.kamenskuserpool.dtos.ResponseCustomerAPIDto
import java.util.*

object ResponseFactory {

    fun generateCustomerAPIResponse(): ResponseCustomerAPIDto =
        ResponseCustomerAPIDto (
            customerId = UUID.randomUUID().toString(),
            localStamp = "2025-05-28T14:30"
        )
}
