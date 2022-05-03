package com.llyods.assignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llyods.assignment.domain.model.TopArtist
import com.llyods.assignment.domain.usecase.TopArtistUseCase
import com.llyods.assignment.getViewStateFlowForNetworkCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopArtistViewModel @Inject constructor(
    private val useCase: TopArtistUseCase,
) : ViewModel() {

    private val _stateFlow =
        MutableStateFlow<ViewState<List<TopArtist>>>(ViewState.Loading(true))


    fun fetchTopArists() {
        viewModelScope.launch {
            getViewStateFlowForNetworkCall {
                useCase(30)
            }.collect {
                when (it) {
                    is ViewState.Loading -> _stateFlow.value = it
                    is ViewState.Failure -> _stateFlow.value = it
                    is ViewState.Success -> {
                        it.output.let { artists ->
                            _stateFlow.value = ViewState.Success(artists)
                        }
                    }
                }
            }
        }
    }

    fun getTopArtists(): StateFlow<ViewState<List<TopArtist>>> = _stateFlow




}