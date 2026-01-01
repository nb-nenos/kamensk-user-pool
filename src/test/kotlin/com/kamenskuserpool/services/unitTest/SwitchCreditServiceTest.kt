package com.kamenskuserpool.services.unitTest

import com.kamenskuserpool.dtos.RequestSwitchCreditDto
import com.kamenskuserpool.exceptions.SwitchFlagException
import com.kamenskuserpool.models.UserModel
import com.kamenskuserpool.repositories.UserRepository
import com.kamenskuserpool.services.SwitchCreditService
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
class SwitchCreditServiceTest {

    @MockK
    lateinit var userService: UserService

    @InjectMockKs
    lateinit var switchCreditService: SwitchCreditService

    @Test
    fun `should switch credit ON`() {

        val user = UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Jaque Line",
            safepayUserId = 287654321672345678L,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "09876543212"
        )

        val dto = RequestSwitchCreditDto(
            customerId = UUID.randomUUID().toString(),
            switchCredit = "on"
        )

        every { userService.findByCustomerId(dto.customerId) }.returns(user)
        every { userService.save(user) }.returns(user)

        val result = switchCreditService.switchCredit(dto)

        assertEquals(result, "Credit On")
        assertTrue { user.creditFlg }
        verify(exactly = 1) { userService.save(user) }
    }

    @Test
    fun `should switch credit OFF`() {

        val user = UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Fulano de Tal",
            safepayUserId = 387654321672395678L,
            creditFlg = true,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "56789012345"
        )

        val dto = RequestSwitchCreditDto(
            customerId = UUID.randomUUID().toString(),
            switchCredit = "off"
        )

        every { userService.findByCustomerId(dto.customerId) }.returns(user)
        every { userService.save(user) }.returns(user)

        val result = switchCreditService.switchCredit(dto)

        assertEquals(result, "Credit Off")
        assertFalse { user.creditFlg }
        verify(exactly = 1) { userService.save(user) }
    }

    @Test
    fun `should throw an Exception`() {

        val user = UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Ciclano",
            safepayUserId = 887654321672395670L,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "78901234567"
        )

        val dto = RequestSwitchCreditDto(
            customerId = user.customerId,
            switchCredit = "Credit exception"
        )

        every { userService.findByCustomerId(dto.customerId) }.returns(user)
        every { userService.save(user) }.returns(user)

        assertThrows<SwitchFlagException> {
            switchCreditService.switchCredit(dto)
        }
    }
}
