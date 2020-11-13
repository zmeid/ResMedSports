package com.example.resmedsports.di.module

import com.example.resmedsports.BuildConfig
import com.example.resmedsports.repository.webservice.SportService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://ancient-wood-1161.getsandbox.com:443/"

@Module
class WebServiceModule {
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    @Provides
    fun providesSportService(retrofit: Retrofit): SportService =
        retrofit.create(SportService::class.java)
}