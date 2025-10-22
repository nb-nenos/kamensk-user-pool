package com.kamenskuserpool.configs.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kamensk-user-pool.address")
data class AddressProperties(
    private val streetName: String,
    private val district: String,
    private val number: String,
    private val complement: String,
    private val postalCode: String,
    private val state: String,
    private val city: String
)
