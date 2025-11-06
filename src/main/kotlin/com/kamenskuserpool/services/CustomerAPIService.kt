package com.kamenskuserpool.services

import com.kamenskuserpool.clients.CustomerAPIClient
import com.kamenskuserpool.dtos.ResponseUserDto
import com.kamenskuserpool.dtos.UserDto
import com.kamenskuserpool.exceptions.ClientException
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CustomerAPIService(
    private val customerAPIClient: CustomerAPIClient
) {
    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun createUser(userDto: UserDto): ResponseUserDto {
        try {
            return customerAPIClient.createUser(userDto)
        } catch (ex: FeignException) {
            logger.error("Error: falha ao comunicar com a API externa.")
            throw ClientException()
        }
    }
}
