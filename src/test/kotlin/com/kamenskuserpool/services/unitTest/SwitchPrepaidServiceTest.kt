package com.kamenskuserpool.services.unitTest

import com.kamenskuserpool.dtos.RequestSwitchPrepaidDto
import com.kamenskuserpool.exceptions.SwitchFlagException
import com.kamenskuserpool.models.UserModel
import com.kamenskuserpool.services.SwitchPrepaidService
import com.kamenskuserpool.services.UserService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
class SwitchPrepaidServiceTest {

    @MockK
    lateinit var userService: UserService

    @InjectMockKs
    lateinit var switchPrepaidService: SwitchPrepaidService

    @Test
    fun `should switch prepaid ON`() {

        val user = UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Kelly",
            safepayUserId = 987654321012345678L,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "75843989034"
        )

        val dto = RequestSwitchPrepaidDto(
            customerId = user.customerId,
            switchPrepaid = "on"
        )

        every { userService.findByCustomerId(dto.customerId) }.returns(user)
        every { userService.save(user) }.returns(user)

        val result = switchPrepaidService.switchPrepaid(dto)

        assertEquals(result, "Prepaid On")
       assertTrue { user.prepaidFlg }
    }

    @Test
    fun `should switch prepaid OFF`() {

        val user = UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Carla",
            safepayUserId = 987654321012345678L,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = true,
            cpf = "75843989034"
        )

        val dto = RequestSwitchPrepaidDto(
            customerId = user.customerId,
            switchPrepaid = "off"
        )

        every { userService.findByCustomerId(dto.customerId) }.returns(user)
        every { userService.save(user) }.returns(user)

        val result = switchPrepaidService.switchPrepaid(dto)

        assertEquals("Prepaid Off", result)
        assertFalse { user.prepaidFlg }
    }

    @Test
    fun `should throw Exception`() {

        val user = UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Flavia",
            safepayUserId = 687654321012345678L,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = true,
            cpf = "75843989034"
        )

        val dto = RequestSwitchPrepaidDto(
            customerId = user.customerId,
            switchPrepaid = "Prepaid exception"
        )

        every { userService.findByCustomerId(dto.customerId) }.returns(user)
        every { userService.save(user) }.returns(user)

        assertThrows<SwitchFlagException> {
            switchPrepaidService.switchPrepaid(dto)
        }
    }

}
