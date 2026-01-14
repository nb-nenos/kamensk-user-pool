package com.kamenskuserpool.integrationTest

import com.kamenskuserpool.dtos.RequestSwitchPrepaidDto
import com.kamenskuserpool.exceptions.SwitchFlagException
import com.kamenskuserpool.models.UserModel
import com.kamenskuserpool.services.SwitchPrepaidService
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
class SwitchPrepaidServiceIntegrationTest {

    @Autowired
    lateinit var switchPrepaidService: SwitchPrepaidService

    @Autowired
    lateinit var userService: UserService

    @Test
    fun `should switch prepaid ON`() {

        val user = userService.save(
            UserModel(
                customerId = UUID.randomUUID().toString(),
                prepaidFlg = false
            )
        )

        val dto = RequestSwitchPrepaidDto(
            customerId = user.customerId,
            switchPrepaid = "on"
        )

        val response = switchPrepaidService.switchPrepaid(dto)

        val updateUser = userService.findByCustomerId(dto.customerId)

        assertEquals("Prepaid On", response)
        assertEquals(updateUser.prepaidFlg, true)
    }

    @Test
    fun `should switch prepaid OFF`() {

        val user = userService.save(
            UserModel(
                customerId = UUID.randomUUID().toString(),
                prepaidFlg = true
            )
        )

        val dto = RequestSwitchPrepaidDto(
            customerId = user.customerId,
            switchPrepaid = "off"
        )

        val response = switchPrepaidService.switchPrepaid(dto)

        val updateUser = userService.findByCustomerId(dto.customerId)

        assertEquals("Prepaid Off", response)
        assertEquals(updateUser.prepaidFlg, false)
    }

    @Test
    fun `should throw Exception`() {

        val user = userService.save(
            UserModel(
                customerId = UUID.randomUUID().toString(),
                prepaidFlg = false
            )
        )

        val dto = RequestSwitchPrepaidDto(
            customerId = user.customerId,
            switchPrepaid = "banana"
        )

        assertThrows<SwitchFlagException> {
            switchPrepaidService.switchPrepaid(dto)
        }

    }
}
