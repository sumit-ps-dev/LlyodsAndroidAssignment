package com.llyods.assignment.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.llyods.assignment.TestCoroutineRule
import com.llyods.assignment.data.response.ApiResult
import com.llyods.assignment.data.response.ArtistsResponse
import com.llyods.assignment.data.response.Image
import com.llyods.assignment.domain.model.Artist
import com.llyods.assignment.domain.usecase.TopArtistUseCase
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TopArtistViewModelTest {

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = TestCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var mockArtistList: List<Artist>

    @RelaxedMockK
    private lateinit var viewStateObserver: Observer<ViewState<List<Artist>>>

    @RelaxedMockK
    private lateinit var mockException: Exception

    @RelaxedMockK
    private lateinit var mockUseCase: TopArtistUseCase

    private val fakeSuccessFlow = flow {
        emit(ApiResult.OnSuccess(mockArtistList))
    }

    private val fakeFailureFlow = flow {
        emit(ApiResult.OnFailure(mockException))
    }

    private lateinit var viewModel: TopArtistViewModel


    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        every { mockException.message } returns "Exception!!"
        viewModel = TopArtistViewModel(mockUseCase)
    }

    @Test
    fun `fetch top artist from network`(){
         runBlockingTest {
             val artistList: ArrayList<Artist> = mockk()
             coEvery { mockUseCase.execute(30) } returns fakeSuccessFlow

             viewModel.artistLiveData.observeForever(viewStateObserver)
             viewModel.fetchTopArists()

             verifyOrder {
                 viewStateObserver.onChanged(ViewState.Loading(true))
                 viewStateObserver.onChanged(ViewState.Success(artistList))
                 viewStateObserver.onChanged(ViewState.Loading(false))
             }
         }

    }

    @Test
    fun `failed to fetch artist from network`(){

        runBlockingTest {
            coEvery { mockUseCase.execute(30) } returns fakeFailureFlow

            viewModel.artistLiveData.observeForever(viewStateObserver)
            viewModel.fetchTopArists()

            verifyOrder {
                viewStateObserver.onChanged(ViewState.Loading(true))
                viewStateObserver.onChanged(ViewState.Failure(mockException))
                viewStateObserver.onChanged(ViewState.Loading(false))
            }
        }
    }


}