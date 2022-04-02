package com.android.mvvm_cleanarchitecture.di

import com.android.mvvm_cleanarchitecture.network.IWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesBaseUrl():String  {
        return "";
    }

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOKHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient().apply {
        OkHttpClient.Builder().run {
            callTimeout(40,TimeUnit.SECONDS)
            connectTimeout(40,TimeUnit.SECONDS)
            readTimeout(40,TimeUnit.SECONDS)
            writeTimeout(40,TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            build()
        }
    }


    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()


    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient, baseUrl: String, moshiConverterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    fun provideRestApiService(retrofit: Retrofit): IWebService {
        return retrofit.create(IWebService::class.java)
    }


}