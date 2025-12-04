package com.kamenskuserpool.services

import com.kamenskuserpool.clients.PsCoreClient
import com.kamenskuserpool.dtos.ResponsePsCoreDto
import com.kamenskuserpool.dtos.UserDto
import com.kamenskuserpool.exceptions.SafepayUserIdException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PsCoreService(
    private val psCoreClient: PsCoreClient
) {
    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun createSafepayUserId(userDto: UserDto): ResponsePsCoreDto{
        return runCatching {
            psCoreClient.getSafepayUserId(userDto.email)
        }.onFailure {
            logger.error("Error: falha ao comunicar com a API externa.")
            throw SafepayUserIdException()
        }.getOrThrow()
    }
}
