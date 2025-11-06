package com.kamenskuserpool.mappers

import com.kamenskuserpool.dtos.UserDto
import com.kamenskuserpool.models.UserModel
import java.time.LocalDateTime

fun UserDto.toUserModel(customerId: String, safepayUserId: Long): UserModel =
    UserModel(
        customerId = customerId,
        safepayUserId = safepayUserId,
        creditFlg = false,
        accountFlg = true,
        prepaidFlg = false,
        dateCreation = LocalDateTime.now(),
        dateUpdate = LocalDateTime.now(),
        cpf = this.cpf,
        fullName = this.fullName
    )
