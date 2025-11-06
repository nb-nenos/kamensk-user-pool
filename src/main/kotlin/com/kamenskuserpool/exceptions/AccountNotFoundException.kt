package com.kamenskuserpool.exceptions

class AccountNotFoundException(message: String = "Account does not exist.") : RuntimeException(message)
