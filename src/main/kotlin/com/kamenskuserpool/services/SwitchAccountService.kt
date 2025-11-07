package com.kamenskuserpool.services

import com.kamenskuserpool.dtos.RequestSwitchAccountDto
import com.kamenskuserpool.exceptions.InvalidRequestException
import com.kamenskuserpool.exceptions.UserNotFoundException
import com.kamenskuserpool.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class SwitchAccountService(
    private val userRepository: UserRepository,
) {

    fun switchAccount(switchAccountPayload: RequestSwitchAccountDto): String {
        val customerAccount = userRepository.findByCustomerId(switchAccountPayload.customerId)
            ?: throw UserNotFoundException()

        if (switchAccountPayload.switchAccount == "on") {
            customerAccount.accountFlg = true
            userRepository.save(customerAccount)
            return "Account on"
        } else if (switchAccountPayload.switchAccount == "off") {
            customerAccount.accountFlg = false
            userRepository.save(customerAccount)
            return "Account off"
        } else {
            throw InvalidRequestException("Insert a valid value (on, off)")
        }
    }
}
