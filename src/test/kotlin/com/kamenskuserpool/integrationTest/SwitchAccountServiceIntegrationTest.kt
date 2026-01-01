package com.kamenskuserpool.integrationTest

import com.kamenskuserpool.dtos.RequestSwitchAccountDto
import com.kamenskuserpool.models.UserModel
import com.kamenskuserpool.repositories.UserRepository
import com.kamenskuserpool.services.SwitchAccountService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class SwitchAccountServiceIntegrationTest {

    @Autowired
    lateinit var switchAccountService: SwitchAccountService

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `should switch account ON`() {

        val user = userRepository.save(
            UserModel(
                customerId = UUID.randomUUID().toString(),
                accountFlg = false
            )
        )

        val dto = RequestSwitchAccountDto(
            customerId = user.customerId,
            switchAccount = "on"
        )

        val response = switchAccountService.switchAccount(dto)

        val updateUser = userRepository.findByCustomerId(user.customerId)

        assertEquals("Account on", response)
        assertEquals(updateUser!!.accountFlg, true)
    }

    @Test
    fun `should switch account OFF`() {

        val user = userRepository.save(
            UserModel(
                customerId = UUID.randomUUID().toString(),
                accountFlg = true
            )
        )

        val dto = RequestSwitchAccountDto(
            customerId = user.customerId,
            switchAccount = "off"
        )

        val response = switchAccountService.switchAccount(dto)

        val updateUser = userRepository.findByCustomerId(user.customerId)

        assertEquals("Account off", response)
        assertEquals(updateUser!!.accountFlg, false)
    }

    @Test
    fun `should throw an Exception`() {




    }
}
