package com.ecoist.market.di.network

import android.content.Context
import com.ecoist.market.BuildConfig
import com.ecoist.market.domain.api.ApiService
import com.ecoist.market.domain.api.ApiServiceTelegram
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


fun networkModule() = module {
    single {
        val okHttpClient = createOkHttpClient(androidApplication())
        val retrofit = getBaseRetrofit(okHttpClient, androidApplication())
        retrofit.create(ApiService::class.java)
    }
    single {
        val okHttpClient = createOkHttpClient(androidApplication())
        val retrofitTelegram = getBaseRetrofitForTelegram(okHttpClient, androidApplication())
        retrofitTelegram.create(ApiServiceTelegram::class.java)
    }
}

fun createOkHttpClient(context: Context): OkHttpClient.Builder {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        /*.authenticator(TokenRefreshAuthenticator(context))*/
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                    .newBuilder()
                    /*.headers(getHeaders())*/
                    .build()
                return chain.proceed(request)
            }
        })
}

fun getRetrofit(context: Context, okHttpClient: OkHttpClient.Builder, url: String): Retrofit {
    return Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(AppGsonConvertorFactory.create(context))
        .baseUrl(url)
        .client(okHttpClient.build())
        .build()
}

fun getBaseRetrofit(okHttpClient: OkHttpClient.Builder, context: Context): Retrofit {

    val iterator = okHttpClient.interceptors().iterator()
    while (iterator.hasNext()) {
        val interceptor = iterator.next()
        if (interceptor is MiddlewareInterceptor)
            iterator.remove()
    }

    okHttpClient.addInterceptor(MiddlewareInterceptor())
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
    return getRetrofit(context, okHttpClient, BuildConfig.API_BASE_URL)
}

fun getBaseRetrofitForTelegram(okHttpClient: OkHttpClient.Builder, context: Context): Retrofit {
    val iterator = okHttpClient.interceptors().iterator()
    while (iterator.hasNext()) {
        val interceptor = iterator.next()
        if (interceptor is MiddlewareInterceptor)
            iterator.remove()
    }

    okHttpClient.addInterceptor(MiddlewareInterceptor())
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
    return getRetrofit(context, okHttpClient, "https://api.telegram.org/bot621215509:AAHSUiHESgLrqGh--MWOK251DprvM9caMLM/")
}