package com.kamenskuserpool.services

import com.kamenskuserpool.clients.SafepayUserIdClient
import com.kamenskuserpool.dtos.ResponseSafepayUserIdDto
import com.kamenskuserpool.dtos.UserDto
import com.kamenskuserpool.exceptions.SafepayUserIdException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SafepayUserIdService(
    private val safepayUserIdClient: SafepayUserIdClient
) {
    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun createSafepayUserId(userDto: UserDto): ResponseSafepayUserIdDto{
        return runCatching {
            safepayUserIdClient.getSafepayUserId(userDto.email)
        }.onFailure {
            logger.error("Error: falha ao comunicar com a API externa.")
            throw SafepayUserIdException()
        }.getOrThrow()
    }
}
