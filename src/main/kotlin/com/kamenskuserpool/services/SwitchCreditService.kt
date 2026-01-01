package com.kamenskuserpool.services

import com.kamenskuserpool.dtos.RequestSwitchCreditDto
import com.kamenskuserpool.exceptions.SwitchFlagException
import com.kamenskuserpool.exceptions.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class SwitchCreditService(
    private val userService: UserService
) {

    fun switchCredit(switchCreditPayload: RequestSwitchCreditDto): String {
        val creditCustomer = userService.findByCustomerId(switchCreditPayload.customerId)
            ?: throw UserNotFoundException()

        when (switchCreditPayload.switchCredit) {
            "on" -> {
                creditCustomer.creditFlg = true
                userService.save(creditCustomer)
                return "Credit On"
            }
            "off" -> {
                creditCustomer.creditFlg = false
                userService.save(creditCustomer)
                return "Credit Off"
            }
            else -> {
                throw SwitchFlagException()
            }
        }
    }
}
