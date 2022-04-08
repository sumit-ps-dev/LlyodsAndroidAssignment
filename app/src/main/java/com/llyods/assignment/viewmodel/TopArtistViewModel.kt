package com.llyods.assignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llyods.assignment.data.response.Artist
import com.llyods.assignment.domain.TopArtistUseCase
import com.llyods.assignment.getViewStateFlowForNetworkCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopArtistViewModel @Inject constructor(
    private val useCase: TopArtistUseCase,
) : ViewModel() {

    val artistLiveData: MutableLiveData<ViewState<List<Artist>>> by lazy {
        MutableLiveData<ViewState<List<Artist>>>()
    }


    fun fetchTopArists() {
        viewModelScope.launch {
            getViewStateFlowForNetworkCall {
                useCase.execute(30)
            }.collect {
                when (it) {
                    is ViewState.Loading -> {
                        artistLiveData.value = it
                    }
                    is ViewState.Failure ->{
                        artistLiveData.value = it
                    }
                    is ViewState.Success ->{
                        it.output.artists.artist.let { artists ->
                            artistLiveData.value = ViewState.Success(artists)
                        }
                    }
                }
            }
        }
    }



}