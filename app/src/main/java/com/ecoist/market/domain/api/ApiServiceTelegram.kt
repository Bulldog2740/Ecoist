package com.ecoist.market.domain.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServiceTelegram {
    @FormUrlEncoded
    @POST("sendMessage")
    suspend fun toTelegramSend(@Field("chat_id") id:String ,@Field("text") name: String?)
}