package com.android.mvvm_cleanarchitecture.di

import com.android.mvvm_cleanarchitecture.contract.IWebService
import com.android.mvvm_cleanarchitecture.domain.TopArtistUseCase
import com.android.mvvm_cleanarchitecture.network.ApiService
import com.android.mvvm_cleanarchitecture.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesBaseUrl(): String {
        return "https://ws.audioscrobbler.com/";
    }

    /**
     * Returns a [IWebService] impl -> RetrofitService
     */
    @Provides
    fun provideRetrofitService(retrofit: ApiService): IWebService =
        RetrofitService(retrofit)

    /**
     * Returns a [TopArtistUseCase] instance
     */
    @Provides
    fun provideTopArtistUseCase(webService: IWebService): TopArtistUseCase =
        TopArtistUseCase(webService)

}