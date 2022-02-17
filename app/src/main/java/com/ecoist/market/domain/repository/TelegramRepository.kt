package com.ecoist.market.domain.repository

import com.ecoist.market.domain.api.ApiService
import com.ecoist.market.domain.api.ApiServiceTelegram
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TelegramRepository(private val apiService: ApiServiceTelegram) {
    val io: CoroutineDispatcher
        get() = Dispatchers.IO

    suspend fun telegramSend(message: String) {
        apiService.toTelegramSend("541974507",message)
            // 541974507 479167907
    }
}