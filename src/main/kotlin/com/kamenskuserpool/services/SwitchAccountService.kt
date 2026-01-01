package com.kamenskuserpool.services

import com.kamenskuserpool.dtos.RequestSwitchAccountDto
import com.kamenskuserpool.exceptions.InvalidRequestException
import com.kamenskuserpool.exceptions.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class SwitchAccountService(
    private val userService: UserService,
) {

    fun switchAccount(switchAccountPayload: RequestSwitchAccountDto): String {
        val customerAccount = userService.findByCustomerId(switchAccountPayload.customerId)
            ?: throw UserNotFoundException()

        when (switchAccountPayload.switchAccount) {
            "on" -> {
                customerAccount.accountFlg = true
                userService.save(customerAccount)
                return "Account on"
            }
            "off" -> {
                customerAccount.accountFlg = false
                userService.save(customerAccount)
                return "Account off"
            }
            else -> {
                throw InvalidRequestException("Insert a valid value (on, off)")
            }
        }
    }
}
