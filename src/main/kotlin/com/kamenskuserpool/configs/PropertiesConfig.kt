package com.kamenskuserpool.configs

import com.kamenskuserpool.configs.properties.AddressProperties
import com.kamenskuserpool.configs.properties.InfoProperties
import com.kamenskuserpool.configs.properties.PhoneNumberProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(
    value = [
        InfoProperties::class,
        AddressProperties::class,
        PhoneNumberProperties::class
    ]
)
class PropertiesConfig
