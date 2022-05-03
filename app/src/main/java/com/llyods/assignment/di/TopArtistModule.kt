package com.llyods.assignment.di

import com.llyods.assignment.domain.model.TopArtist
import com.llyods.assignment.presentation.views.adapter.ItemClickListener
import com.llyods.assignment.presentation.views.adapter.TopArtistAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object TopArtistModule {

    @Provides
    fun provideTopArtistAdapter(): TopArtistAdapter{
        return TopArtistAdapter()
    }


}