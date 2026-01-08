package com.kamenskuserpool.integrationTest

import com.kamenskuserpool.dtos.RequestSwitchCreditDto
import com.kamenskuserpool.exceptions.SwitchFlagException
import com.kamenskuserpool.models.UserModel
import com.kamenskuserpool.services.SwitchCreditService
import com.kamenskuserpool.services.UserService
import jakarta.transaction.Transactional
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class SwitchCreditServiceIntegrationTest {

    @Autowired
    lateinit var switchCreditService: SwitchCreditService

    @Autowired
    lateinit var userService: UserService

    @Test
    fun `should switch credit ON`() {

        val user = userService.save(
            UserModel(
                customerId = UUID.randomUUID().toString(),
                creditFlg = false
            )
        )

        val dto = RequestSwitchCreditDto(
            customerId = user.customerId,
            switchCredit = "on"
        )

        val response = switchCreditService.switchCredit(dto)

        val updateUser = userService.findByCustomerId(user.customerId)

        assertEquals("Credit On", response)
        assertEquals(updateUser!!.creditFlg, true)
    }

    @Test
    fun `should switch credit OFF`() {

        val user = userService.save(
            UserModel(
                customerId = UUID.randomUUID().toString(),
                creditFlg = true
            )
        )

        val dto = RequestSwitchCreditDto(
            customerId = user.customerId,
            switchCredit = "off"
        )

        val response = switchCreditService.switchCredit(dto)

        val updateUser = userService.findByCustomerId(user.customerId)

        assertEquals("Credit Off", response)
        assertEquals(updateUser!!.creditFlg, false)
    }

    @Test
    fun `should throw an Exception`() {

        val user = UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Natan Pires",
            safepayUserId = 123,
            creditFlg = true,
            cpf = "78032156745"
        )

        userService.save(user)

        val request = RequestSwitchCreditDto(
            customerId = user.customerId,
            switchCredit = "banana"
        )

        assertThrows<SwitchFlagException> {
            switchCreditService.switchCredit(request)
        }
    }
}
