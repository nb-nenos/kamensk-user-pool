package com.kamenskuserpool.services

import com.kamenskuserpool.clients.CustomerAPIClient
import com.kamenskuserpool.dtos.ResponseCustomerAPIDto
import com.kamenskuserpool.dtos.UserDto
import com.kamenskuserpool.exceptions.ClientException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CustomerAPIService(
    private val customerAPIClient: CustomerAPIClient
) {
    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun createUser(userDto: UserDto): ResponseCustomerAPIDto{
        return runCatching {
            customerAPIClient.createUser(userDto)
        }.onFailure {
            logger.error("Error: falha ao comunicar com a API externa.")
            throw ClientException()
        }.getOrThrow()
    }
}
