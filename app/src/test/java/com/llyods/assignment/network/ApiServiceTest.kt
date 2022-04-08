package com.llyods.assignment.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.llyods.assignment.TestCoroutineRule
import com.llyods.assignment.data.ApiResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ApiServiceTest: ApiAbstract<ApiService>() {

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = TestCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: ApiService

    @Before
    fun initService(){
        service = createService(ApiService::class.java)
    }

    @Test
    fun `get top artists from network`() = runBlocking{
       enqueueResponse("/ArtistResponse.json")
        val response = service.getTopArtists(limit = 10)
        val apiRespose = ApiResult.OnSuccess(response)

        val responseBody  = requireNotNull(apiRespose.data.body()?.artists?.artist)
        mockWebServer.takeRequest()

       assertEquals(10, responseBody.count())
       val artist = responseBody[0]

        assertEquals("The Weeknd", artist.name)
        assertEquals("275632394", artist.playcount)
        assertEquals("2501047", artist.listeners)
        assertEquals("https://www.last.fm/music/The+Weeknd", artist.url)

    }

}