package com.android.mvvm_cleanarchitecture.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.android.mvvm_cleanarchitecture.R
import com.android.mvvm_cleanarchitecture.viewmodel.TopArtistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleActivity : AppCompatActivity() {

//    private val viewModel: SampleViewModel by viewModels()
    private val  viewModel: TopArtistViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchTopArists()
    }
}