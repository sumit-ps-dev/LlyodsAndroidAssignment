package com.llyods.assignment.data

import com.llyods.assignment.data.response.ApiResult
import com.llyods.assignment.data.response.Artist
import io.mockk.mockk
import org.junit.Test
import java.io.IOException
import org.junit.Assert.*
class ApiResultTest {

    @Test
    fun `test OnSuccess of ApiResult for a vaild data`(){

        val artist = mockk<Artist>()

        val success = ApiResult.OnSuccess(artist)
        assertTrue(success is ApiResult.OnSuccess)
    }

    @Test
    fun `test OnFailure of ApiResult when there is a exception`() {
        val error = ApiResult.OnFailure(IOException("error message"))
        assertTrue(error is ApiResult.OnFailure)
    }

}