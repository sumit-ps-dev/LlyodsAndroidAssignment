package com.android.mvvm_cleanarchitecture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mvvm_cleanarchitecture.ViewState
import com.android.mvvm_cleanarchitecture.data.response.Artist
import com.android.mvvm_cleanarchitecture.domain.TopArtistUseCase
import com.android.mvvm_cleanarchitecture.getViewStateFlowForNetworkCall
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
                    is ViewState.Loading -> artistLiveData.value = it
                    is ViewState.Failure -> artistLiveData.value = it
                    is ViewState.Success -> it.output.artists?.artist?.let { artists ->
                        artistLiveData.value = ViewState.Success(artists)
                    }
                }
            }
        }
    }

}