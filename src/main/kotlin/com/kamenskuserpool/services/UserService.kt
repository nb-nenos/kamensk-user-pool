package com.kamenskuserpool.services

import com.kamenskuserpool.exceptions.CreditNotFoundException
import com.kamenskuserpool.exceptions.PrepaidNotFoundException
import com.kamenskuserpool.exceptions.UserNotFoundException
import com.kamenskuserpool.mappers.toUserModel
import com.kamenskuserpool.models.UserModel
import com.kamenskuserpool.repositories.UserRepository
import com.kamenskuserpool.utils.UserUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(
    private val customerAPIService: CustomerAPIService,
    private val userRepository: UserRepository,
    private val userUtils: UserUtils,
    private val safepayUserIdService: SafepayUserIdService
) {

    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun createUser() {
        logger.info("Starting user creation")

        val userDto = userUtils.userDtoFactory()

        val createUserClientResponse = customerAPIService.createUser(userDto)

        val safepayUserId = safepayUserIdService.createSafepayUserId(userDto)

        val userModel = userDto.toUserModel(createUserClientResponse.customerId, safepayUserId.SafePayUserId)

        logger.info("Saving user in data base.")

        userRepository.save(userModel)

        logger.info("User created successfully! ID: ${userModel.id}")
    }

    fun getRandomUsers(): UserModel {
        logger.info("Searching for random user...")
        return userRepository.findRandomUser()
            ?: run {
                logger.error("User not found.")
                throw UserNotFoundException()
            }
    }

    fun getUserWithCredit(): UserModel {
        logger.info("Searching for user with Credit Flag...")
        return userRepository.findByCreditTrue()
            ?: run {
                logger.error("User with no Credit Flag.")
                throw CreditNotFoundException()
            }
    }

    fun getPrepaidUser(): UserModel {
        logger.info("Searching for user with Prepaid Flag")
        return userRepository.findByIsPrepaidTrue()
            ?: run {
                logger.error("User with no Prepaid Flag.")
                throw PrepaidNotFoundException()
            }
    }
}
