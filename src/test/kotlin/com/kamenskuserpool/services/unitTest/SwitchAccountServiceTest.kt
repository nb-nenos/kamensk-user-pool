package com.kamenskuserpool.services.unitTest

import com.kamenskuserpool.dtos.RequestSwitchAccountDto
import com.kamenskuserpool.exceptions.SwitchFlagException
import com.kamenskuserpool.models.UserModel
import com.kamenskuserpool.services.SwitchAccountService
import com.kamenskuserpool.services.UserService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
class SwitchAccountServiceTest {

    @MockK
    lateinit var userService: UserService

    @InjectMockKs
    lateinit var switchAccountService: SwitchAccountService

    @Test
    fun `should switch account ON`() {

        val user = UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Joana Joaninha",
            safepayUserId = 987654321012345678L,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "12345678910"
        )

        val dto = RequestSwitchAccountDto(
            customerId = UUID.randomUUID().toString(),
            switchAccount = "on"
        )

        every { userService.findByCustomerId(dto.customerId) }.returns(user)
        every { userService.save(user) }.returns(user)

        val result = switchAccountService.switchAccount(dto)

        assertEquals(result, "Account on")
        assertTrue { user.accountFlg }
        verify(exactly = 1) { userService.save(user) }
    }

    @Test
    fun `should switch account OFF`() {

        val user = UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Maria Mariazinha",
            safepayUserId = 587624326012385678L,
            creditFlg = false,
            accountFlg = true,
            prepaidFlg = false,
            cpf = "45678901234"
        )

        val dto = RequestSwitchAccountDto(
            customerId = UUID.randomUUID().toString(),
            switchAccount = "off"
        )

        every { userService.findByCustomerId(dto.customerId) }.returns(user)
        every { userService.save(user) }.returns(user)

        val result = switchAccountService.switchAccount(dto)

        assertEquals(result, "Account off")
        assertFalse { user.accountFlg }
        verify(exactly = 1) { userService.save(user) }
    }

    @Test
    fun `should throw an Exception`() {

        val user = UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Carol Lina",
            safepayUserId = 145624326012385672L,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "78901234567"
        )

        val dto = RequestSwitchAccountDto(
            customerId = UUID.randomUUID().toString(),
            switchAccount = "Account exception"
        )

        every { userService.findByCustomerId(dto.customerId) }.returns(user)
        every { userService.save(user) }.returns(user)

        assertThrows<SwitchFlagException> {
            switchAccountService.switchAccount(dto)
        }
    }
}
