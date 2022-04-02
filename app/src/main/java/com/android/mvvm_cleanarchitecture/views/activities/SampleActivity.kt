package com.android.mvvm_cleanarchitecture.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.mvvm_cleanarchitecture.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleActivity : AppCompatActivity() {

    lateinit var testString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}