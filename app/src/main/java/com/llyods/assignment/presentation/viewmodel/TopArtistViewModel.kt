package com.llyods.assignment.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llyods.assignment.domain.model.TopArtist
import com.llyods.assignment.domain.usecase.TopArtistUseCase
import com.llyods.assignment.getViewStateFlowForNetworkCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopArtistViewModel @Inject constructor(
    private val useCase: TopArtistUseCase,
) : ViewModel() {

    private val _artistLiveData: MutableLiveData<ViewState<List<TopArtist>>> by lazy {
        MutableLiveData<ViewState<List<TopArtist>>>()
    }


    fun fetchTopArists() {
        viewModelScope.launch {
            getViewStateFlowForNetworkCall {
                useCase(30)
            }.collect {
                when (it) {
                    is ViewState.Loading -> _artistLiveData.value = it
                    is ViewState.Failure -> _artistLiveData.value = it
                    is ViewState.Success -> {
                        it.output.let { artists ->
                            _artistLiveData.value = ViewState.Success(artists)
                        }
                    }
                }
            }
        }
    }

    fun getTopArtists(): LiveData<ViewState<List<TopArtist>>> = _artistLiveData

}