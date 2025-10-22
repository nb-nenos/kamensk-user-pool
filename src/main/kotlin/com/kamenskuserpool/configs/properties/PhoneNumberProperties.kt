package com.kamenskuserpool.configs.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("kamensk-user-pool.phones")
data class PhoneNumberProperties (
    private val countryCode: String,
    private val areaCode: String,
    private val number: String
)
