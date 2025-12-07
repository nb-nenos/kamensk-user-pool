package com.kamenskuserpool.utils.stubs

import com.github.tomakehurst.wiremock.client.WireMock.*
import org.springframework.stereotype.Component

@Component
class StubPsCore {

    fun stubPsCore(email: String) {

        stubFor(
            get(urlEqualTo("/$email"))
                .willReturn(
                    aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(
                            """
                            {
                                "SafePayUserId": 2916159419615132624
                            }
                            """
                        )
                )
        )
    }
}
