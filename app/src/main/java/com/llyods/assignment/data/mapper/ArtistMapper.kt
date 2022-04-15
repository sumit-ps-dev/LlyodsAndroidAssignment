package com.llyods.assignment.data.mapper

import com.llyods.assignment.data.response.ArtistsResponse
import com.llyods.assignment.domain.model.Artist

object ArtistMapper {

    fun ArtistsResponse.toTopArtist(): List<Artist> {
        val topArtists = ArrayList<Artist>()
        artists.artist.forEach {
            topArtists.add(Artist(it.name, it.playcount, it.listeners, it.url,it.image))
        }
        return topArtists
    }

}