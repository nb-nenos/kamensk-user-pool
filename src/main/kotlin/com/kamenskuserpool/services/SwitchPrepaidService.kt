package com.kamenskuserpool.services

import com.kamenskuserpool.dtos.RequestSwitchPrepaidDto
import com.kamenskuserpool.exceptions.SwitchFlagException
import com.kamenskuserpool.exceptions.UserNotFoundException
import com.kamenskuserpool.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class SwitchPrepaidService(
    private val userService: UserService
) {

    fun switchPrepaid(switchPrepaidPayload: RequestSwitchPrepaidDto): String {
        val customer = userService.findByCustomerId(switchPrepaidPayload.customerId)

        when (switchPrepaidPayload.switchPrepaid) {
            "on" -> {
                customer.prepaidFlg = true
                userService.save(customer)
                return "Prepaid On"
            }
            "off" -> {
                customer.prepaidFlg = false
                userService.save(customer)
                return "Prepaid Off"
            }
            else -> {
                throw SwitchFlagException()
            }
        }
    }
}
