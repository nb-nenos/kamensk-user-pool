package com.kamenskuserpool.services

import com.kamenskuserpool.dtos.RequestSwitchCreditDto
import com.kamenskuserpool.exceptions.SwitchFlagException
import com.kamenskuserpool.exceptions.UserNotFoundException
import com.kamenskuserpool.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class SwitchCreditService(
    private val userRepository: UserRepository
) {

    fun switchCredit(switchCreditPayload: RequestSwitchCreditDto): String {
        val creditCustomer = userRepository.findByCustomerId(switchCreditPayload.customerId)
            ?: throw UserNotFoundException()

        when (switchCreditPayload.switchCredit) {
            "on" -> {
                creditCustomer.creditFlg = true
                userRepository.save(creditCustomer)
                return "Credit On"
            }
            "off" -> {
                creditCustomer.creditFlg = false
                userRepository.save(creditCustomer)
                return "Credit Off"
            }
            else -> {
                throw SwitchFlagException()
            }
        }
    }
}
