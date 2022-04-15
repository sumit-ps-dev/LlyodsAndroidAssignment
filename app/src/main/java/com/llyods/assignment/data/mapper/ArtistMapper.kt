package com.llyods.assignment.data.mapper

import com.llyods.assignment.data.response.ArtistsResponse
import com.llyods.assignment.domain.model.TopArtist

object ArtistMapper {

    fun ArtistsResponse.toTopArtist(): List<TopArtist> {
        val topArtists = ArrayList<TopArtist>()
        artists.artist.forEach {
            topArtists.add(TopArtist(it.name, it.playcount, it.listeners, it.url,it.image))
        }
        return topArtists
    }

}