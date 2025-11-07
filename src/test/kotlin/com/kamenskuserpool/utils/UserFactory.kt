package com.kamenskuserpool.utils

import com.kamenskuserpool.models.UserModel
import java.util.UUID

object UserFactory {

    fun generateUser(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Maria",
            safepayUserId = 2,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "12345678910"
        )
}
