package com.kamenskuserpool.services.unitTest

import com.kamenskuserpool.exceptions.CreditNotFoundException
import com.kamenskuserpool.exceptions.PrepaidNotFoundException
import com.kamenskuserpool.exceptions.UserNotFoundException
import com.kamenskuserpool.models.UserModel
import com.kamenskuserpool.repositories.UserRepository
import com.kamenskuserpool.services.CustomerAPIService
import com.kamenskuserpool.services.PsCoreService
import com.kamenskuserpool.services.UserService
import com.kamenskuserpool.utils.UserFactory
import com.kamenskuserpool.utils.UserUtils
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
class UserServiceTest(

    @MockK
    private val userRepository: UserRepository,

    @MockK
    private val customerAPIService: CustomerAPIService,

    @MockK
    private val userUtils: UserUtils,

    @MockK
    private val psCoreService: PsCoreService,

    @InjectMockKs
    private val userService: UserService
) {

    @Test
    fun `should return a random user when requested`() {
        val user1 = UserFactory.generateRandomUser1()
        val user2 = UserFactory.generateRandomUser2()

        every { userRepository.findRandomUser() } returns user1

        val result = userService.getRandomUsers()

        assertNotNull(result)
        assertEquals(result, user1)
    }

    @Test
    fun `should throw Exception when no random user exists`() {

        every { userRepository.findRandomUser() } returns null

        assertThrows<UserNotFoundException> {
            userService.getRandomUsers()
        }
    }

    @Test
    fun `should return an user with credit when required`() {
        val creditUser = UserFactory.generateUserFlags(true, true, false)

        every { userRepository.findByCreditTrue() } returns creditUser

        val result = userService.getUserWithCredit()

        assertNotNull(result)
        assertTrue(result.creditFlg)
    }

    @Test
    fun `should throw Exception when no credit user is found`() {

        every { userRepository.findByCreditTrue() } returns null

        assertThrows<CreditNotFoundException> {
            userService.getUserWithCredit()
        }
    }

    @Test
    fun `should return an user with prepaid when requested`() {
        val prepaidUser = UserFactory.generateUserFlags(false, false, true)

        every { userRepository.findByIsPrepaidTrue() } returns prepaidUser

        val result = userService.getPrepaidUser()

        assertNotNull(result)
        assertTrue(result.prepaidFlg)
    }

    @Test
    fun `should throw Exception when no prepaid user is found`() {

        every { userRepository.findByIsPrepaidTrue() } returns null

        assertThrows<PrepaidNotFoundException> {
            userService.getPrepaidUser()
        }
    }
}
