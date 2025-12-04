package com.kamenskuserpool.utils

import com.kamenskuserpool.dtos.ResponseCustomerAPIDto
import com.kamenskuserpool.dtos.ResponsePsCoreDto
import java.util.*

object ResponseFactory {

    fun generateSafepayAPIResponse(): ResponsePsCoreDto =
        ResponsePsCoreDto (
          safePayUserId = 42143243234232
        )

    fun generateCustomerAPIResponse(): ResponseCustomerAPIDto =
        ResponseCustomerAPIDto (
            customerId = UUID.randomUUID().toString(),
            localStamp = "2025-05-28T14:30"
        )
}
