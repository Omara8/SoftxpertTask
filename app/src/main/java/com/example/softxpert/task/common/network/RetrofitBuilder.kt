package com.example.softxpert.task.common.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {

    companion object {

        @Volatile
        private var retrofit: Retrofit? = null

        fun getRetrofit(): Retrofit {
            return retrofit ?: synchronized(this) {
                build()
            }
        }

        private const val baseUrl: String = "http://demo1286023.mockable.io/"

        private fun build(): Retrofit {
            val httpClient = getHttpClientBuilder()
            addHeadersInterceptor(httpClient)
            return getRetroFit(httpClient)
        }

        private fun getRetroFit(httpClient: OkHttpClient.Builder): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create()).client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        private fun getHttpClientBuilder(): OkHttpClient.Builder {
            val httpClient = OkHttpClient().newBuilder()
            httpClient.readTimeout(60, TimeUnit.SECONDS)
            httpClient.connectTimeout(60, TimeUnit.SECONDS)
            return httpClient
        }

        private fun addHeadersInterceptor(httpClient: OkHttpClient.Builder) {
            val interceptor = Interceptor { chain ->
                val builder = chain.request().newBuilder()

                val request = builder.build()
                chain.proceed(request)
            }
            httpClient.networkInterceptors().add(interceptor)
        }

    }

}