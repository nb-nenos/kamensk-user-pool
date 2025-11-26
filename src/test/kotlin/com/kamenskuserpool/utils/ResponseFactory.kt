package com.kamenskuserpool.utils

import com.kamenskuserpool.dtos.ResponseSafepayUserIdDto

object ResponseFactory {

    fun generateSafepayResponse(): ResponseSafepayUserIdDto =
        ResponseSafepayUserIdDto(
            SafePayUserId = 9876543212345
        )
}
