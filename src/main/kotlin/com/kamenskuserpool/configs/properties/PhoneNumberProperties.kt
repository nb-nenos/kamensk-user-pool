package com.kamenskuserpool.configs.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("kamensk-user-pool.phones")
data class PhoneNumberProperties (
    val countryCode: String,
    val areaCode: String,
    val number: String
)
