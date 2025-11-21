package com.kamenskuserpool.utils

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
}
