package com.llyods.assignment.di

import com.llyods.assignment.contract.IWebService
import com.llyods.assignment.domain.TopArtistRepository
import com.llyods.assignment.domain.TopArtistUseCase
import com.llyods.assignment.network.ApiService
import com.llyods.assignment.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /**
     * Returns a [IWebService] impl -> RetrofitService
     */
    @Provides
    fun provideRetrofitService(retrofit: ApiService): IWebService =
        RetrofitService(retrofit)

    /**
     * Returns a [TopArtistRepository] instance
     */
    @Provides
    fun provideTopArtistRepository(webService: IWebService): TopArtistRepository =
        TopArtistRepository(webService)

    /**
     * Returns a [TopArtistUseCase] instance
     */
    @Provides
    fun provideTopArtistUseCase(repository: TopArtistRepository): TopArtistUseCase =
        TopArtistUseCase(repository)

}