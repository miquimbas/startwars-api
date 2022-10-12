package com.br.apimaker.startwarsapi.http.request

import okhttp3.OkHttpClient
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Component
class RetrofitBuilder {
    companion object {
        const val BASE_URL = "https://swapi.dev/api/"
    }

    final inline fun <reified T> build(baseUrl: String): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()

        return retrofit.create(T::class.java)
    }

    fun okHttpClient() = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.MINUTES)
        .connectTimeout(10, TimeUnit.MINUTES)
        .build()
}