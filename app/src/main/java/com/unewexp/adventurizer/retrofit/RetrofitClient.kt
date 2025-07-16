package com.unewexp.adventurizer.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

object RetrofitClient {
    private const val BASE_URL = "https://10.0.2.2:3000/"

    private val unsafeTrustManager = object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    }

    private val unsafeOkHttpClient = OkHttpClient.Builder()
        .hostnameVerifier { _, _ -> true }
        .sslSocketFactory(
            SSLContext.getInstance("TLS").apply {
                init(null, arrayOf(unsafeTrustManager), SecureRandom())
            }.socketFactory,
            unsafeTrustManager
        )
        .build()

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(unsafeOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}