package com.kamenskuserpool

import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8089)
abstract class AbstractIntegrationTest {

    @BeforeEach
    fun beforeEach() {
        WireMock.reset()
    }
}
