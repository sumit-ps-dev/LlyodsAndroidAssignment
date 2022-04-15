package com.llyods.assignment.data

import com.llyods.assignment.data.response.Artist
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class ArtistTest {

    @Test
    fun `validate Artist from Json string`() {

        val artist = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            .adapter(Artist::class.java)
            .fromJson("{\"name\":\"The Weeknd\",\"playcount\":\"276052310\",\"listeners\":\"2502866\",\"mbid\":\"c8b03190-306c-4120-bb0b-6f2ebfc06ea9\",\"url\":\"https://www.last.fm/music/The+Weeknd\",\"streamable\":\"0\",\"image\":[{\"text\":\"https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"small\"},{\"text\":\"https://lastfm.freetls.fastly.net/i/u/64s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"medium\"},{\"text\":\"https://lastfm.freetls.fastly.net/i/u/174s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"large\"},{\"text\":\"https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"extralarge\"},{\"text\":\"https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"mega\"}]}")

        assertEquals("The Weeknd", artist?.name)
        assertEquals("276052310", artist?.playcount)
            assertEquals("2502866", artist?.listeners)
        assertEquals("https://www.last.fm/music/The+Weeknd", artist?.url)
        assertEquals(5,artist?.image?.count())
    }
}