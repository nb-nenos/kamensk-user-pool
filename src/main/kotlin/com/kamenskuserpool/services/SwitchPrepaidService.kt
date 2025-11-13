package com.kamenskuserpool.services

import com.kamenskuserpool.dtos.RequestSwitchPrepaidDto
import com.kamenskuserpool.exceptions.SwitchFlagException
import com.kamenskuserpool.exceptions.UserNotFoundException
import com.kamenskuserpool.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class SwitchPrepaidService(
    private val userRepository: UserRepository
) {

    fun switchPrepaid(switchPrepaidPayload: RequestSwitchPrepaidDto): String {
        val customer = userRepository.findByCustomerId(switchPrepaidPayload.customerId)
            ?: throw UserNotFoundException()


        when (switchPrepaidPayload.switchPrepaid) {
            "on" -> {
                customer.prepaidFlg = true
                userRepository.save(customer)
                return "Prepaid On"
            }
            "off" -> {
                customer.prepaidFlg = false
                userRepository.save(customer)
                return "Prepaid Off"
            }
            else -> {
                throw SwitchFlagException()
            }
        }
    }
}
