package com.kamenskuserpool.utils

import com.kamenskuserpool.configs.properties.AddressProperties
import com.kamenskuserpool.configs.properties.InfoProperties
import com.kamenskuserpool.configs.properties.PhoneNumberProperties
import com.kamenskuserpool.dtos.UserDto
import com.kamenskuserpool.enums.UserTypeEnum
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class UserUtils(
    private val infoProperties: InfoProperties,
    private val addressProperties: AddressProperties,
    private val phoneNumberProperties: PhoneNumberProperties
) {

    fun userDtoFactory(): UserDto =
        UserDto(
            fullName = infoProperties.fullName,
            dateOfBirth = infoProperties.dateOfBirth,
            cpf = generateCpf(),
            email = infoProperties.email,
            password = infoProperties.password,
            motherName = infoProperties.motherName,
            type = UserTypeEnum.PJ,
            safetyPhrase = infoProperties.safetyPhrase,
            address = listOf(addressProperties),
            phoneNumber = listOf(phoneNumberProperties)
        )

    fun generateCpf(): String {
        val result = mutableListOf<Int>()
        while (true) {
            val nums = IntArray(9) { Random.nextInt(0, 10) }
            if (nums.any { it != nums[0] }) {
                result.addAll(nums.toList())
                val d1 = ((nums.mapIndexed { i, d -> d * (10 - i) }.sum() % 11).let { if (it < 2) 0 else 11 - it })
                val d2 = (((nums + d1).mapIndexed { i, d -> d * (11 - i) }.sum() % 11).let { if (it < 2) 0 else 11 - it })
                result.add(d1)
                result.add(d2)
                break
            }
        }
        return result.joinToString("")
    }
}
