package com.kamenskuserpool.utils

import com.kamenskuserpool.configs.properties.AddressProperties
import com.kamenskuserpool.configs.properties.PhoneNumberProperties
import com.kamenskuserpool.dtos.UserDto
import com.kamenskuserpool.enums.UserTypeEnum
import com.kamenskuserpool.models.UserModel
import java.util.*
import kotlin.random.Random

object UserFactory {

    fun generateUserAccountOn(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Maria",
            safepayUserId = 2,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "12345678910"
        )

    fun generateUserAccountOff(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Ana",
            safepayUserId = 3,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "09876543212",
        )

    fun generateUserAccountException(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Carol",
            safepayUserId = 4,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "90876512354"
        )

    fun generateUserCreditOn(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            creditFlg = false
        )

    fun generateUserCreditOff(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            creditFlg = true
        )

    fun generateUserCreditException(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            creditFlg = false
        )

    fun generateUserPrepaidOn(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            prepaidFlg = true
        )

    fun generateUserPrepaidOff(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            prepaidFlg = false
        )

    fun generateUserPrepaidException(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            prepaidFlg = false
        )

    fun generateSafepayUserId(): UserDto =
        UserDto(
            fullName = "Bruna",
            dateOfBirth = "2025-05-28",
            cpf = "12345678990",
            email = "bruna@email.com",
            password = "2805",
            motherName = "Mae",
            address = listOf(
                AddressProperties(
                streetName = "Rua das Flores",
                district = "São Judas",
                number = "213",
                complement = "apt 80",
                postalCode = "04380817",
                state = "São Paulo",
                city = "São Paulo"
                )
            ),
            phoneNumber = listOf(
                PhoneNumberProperties(
                    countryCode = "55",
                    areaCode = "11",
                    number = "983264562"
                )
            ),
            type = UserTypeEnum.PF,
            safetyPhrase = "pergunta aqui"
        )

    fun buildUserDto(): UserDto =
        UserDto(
            fullName = "Bruna",
            dateOfBirth = "2025-05-28",
            cpf = "12341238990",
            email = "brunaa@email.com",
            password = "2805",
            motherName = "Maezinha",
            address = listOf(
                AddressProperties(
                    streetName = "Rua das Rochas",
                    district = "Saúde",
                    number = "213",
                    complement = "apt 70",
                    postalCode = "04090237",
                    state = "São Paulo",
                    city = "São Paulo"
                )
            ),
            phoneNumber = listOf(
                PhoneNumberProperties(
                    countryCode = "55",
                    areaCode = "11",
                    number = "983987562"
                )
            ),
            type = UserTypeEnum.PF,
            safetyPhrase = "pergunte aqui"
        )

    fun generateRandomUser1(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "maria betanea",
            safepayUserId = 787897897899,
            creditFlg = true,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "12345678910",
        )

    fun generateRandomUser2(): UserModel =
        UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "pedro betaneo",
            safepayUserId = 32743023859,
            creditFlg = true,
            accountFlg = true,
            prepaidFlg = false,
            cpf = "98765432109",
        )

    fun generateUserFlags(
        credit: Boolean,
        account: Boolean,
        prePaid: Boolean
    ): UserModel
    {
        val user = UserModel(
                customerId = UUID.randomUUID().toString(),
                fullName = "Ayuiu",
                safepayUserId = 1,
                cpf = generateCpf()
        )

        when (credit) {
            true -> user.creditFlg = true
            false -> user.creditFlg = false
        }
        when (account) {
            true -> user.accountFlg = true
            false -> user.accountFlg = false
        }
        when (prePaid) {
            true -> user.prepaidFlg = true
            false -> user.prepaidFlg = false
        }
        return user
    }

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
