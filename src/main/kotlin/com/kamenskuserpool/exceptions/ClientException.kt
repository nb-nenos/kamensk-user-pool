package com.kamenskuserpool.exceptions

class ClientException(message: String = "Erro ao comunicar com a API externa.") : RuntimeException(message)
