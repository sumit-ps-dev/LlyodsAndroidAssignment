package com.llyods.assignment.di

import com.llyods.assignment.data.network.IWebService
import com.llyods.assignment.data.repository.TopArtistRepository
import com.llyods.assignment.domain.usecase.TopArtistUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

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