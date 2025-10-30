package com.kamenskuserpool.configs.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kamensk-user-pool.address")
data class AddressProperties(
    val street: String,
    val district: String,
    val number: String,
    val complement: String,
    val postalCode: String,
    val state: String,
    val city: String
)
