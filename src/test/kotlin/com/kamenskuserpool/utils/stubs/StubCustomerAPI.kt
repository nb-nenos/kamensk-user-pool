package com.kamenskuserpool.utils.stubs

import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo

class StubCustomerAPI {

    fun stubCustomerAPI() {

        stubFor(
            post(urlEqualTo("/create-user"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody(
                            """
                            {
                                "customerId": "281b22ca-783b-40de-9ccb-c7d23c8a0a8f",
                                "localStamp": "2025-12-03"
                            }
                            """
                        )
                )
        )
    }
}
