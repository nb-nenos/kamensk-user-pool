package com.kamenskuserpool.utils.stubs

import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo

class StubPsCore {

    fun stubPsCore(email: String) {

        stubFor(
            get(urlEqualTo("/$email"))
                .willReturn(
                    aResponse()
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
