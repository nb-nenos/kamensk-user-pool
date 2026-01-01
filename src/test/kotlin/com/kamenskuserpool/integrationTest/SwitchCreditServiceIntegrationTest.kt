package com.kamenskuserpool.integrationTest

import com.kamenskuserpool.dtos.RequestSwitchCreditDto
import com.kamenskuserpool.models.UserModel
import com.kamenskuserpool.repositories.UserRepository
import com.kamenskuserpool.services.SwitchCreditService
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
class SwitchCreditServiceIntegrationTest {

    @Autowired
    lateinit var switchCreditService: SwitchCreditService

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `should switch credit ON`() {

        val user = userRepository.save(
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

        val updateUser = userRepository.findByCustomerId(user.customerId)

        assertEquals("Credit Off", response)
        assertEquals(updateUser!!.creditFlg, true)
    }

    @Test
    fun `should switch credit OFF`() {

        val user = userRepository.save(
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

        val updateUser = userRepository.findByCustomerId(user.customerId)

        assertEquals("Credit Off", response)
        assertEquals(updateUser!!.creditFlg, false)
    }

    @Test
    fun `should throw an Exception`() {

    }
}
