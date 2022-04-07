package com.android.mvvm_cleanarchitecture.viewmodel

import com.android.mvvm_cleanarchitecture.data.response.Artist
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class ViewStateTest {

    @Test
    fun `test Loading of ViewState`(){

        val loading = ViewState.Loading(true)
        assertTrue(loading is ViewState.Loading)
    }

    @Test
    fun `test Success of ViewState on data`(){

        val artist = mockk<Artist>()

        val success = ViewState.Success(artist)
        assertTrue(success is ViewState.Success)
    }

    @Test
    fun `test Failure of ViewState when there is a exception`() {
        val error = ViewState.Failure(IOException("error message"))
        assertTrue(error is ViewState.Failure)
    }


}