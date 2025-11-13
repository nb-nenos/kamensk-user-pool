package com.kamenskuserpool.services

import com.kamenskuserpool.clients.CustomerAPIClient
import com.kamenskuserpool.dtos.ResponseUserDto
import com.kamenskuserpool.dtos.UserDto
import com.kamenskuserpool.exceptions.ClientException
import com.kamenskuserpool.exceptions.SafepayUserIdException
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CustomerAPIService(
    private val customerAPIClient: CustomerAPIClient
) {
    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun createUser(userDto: UserDto): ResponseUserDto{
        return runCatching {
            customerAPIClient.createUser(userDto)
        }.onFailure {
            logger.error("Error: falha ao comunicar com a API externa.")
            throw ClientException()
        }.getOrThrow()
    }
}
