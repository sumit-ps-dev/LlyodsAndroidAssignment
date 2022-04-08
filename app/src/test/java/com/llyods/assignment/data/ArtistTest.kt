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
            .fromJson("{\"name\":\"John\",\"playcount\":\"21\",\"listeners\":\"6789\"}")

        assertEquals("John", artist?.name)
        assertEquals("21", artist?.playcount)
        assertEquals("6789", artist?.listeners)
        assertEquals(null, artist?.url)
    }
}