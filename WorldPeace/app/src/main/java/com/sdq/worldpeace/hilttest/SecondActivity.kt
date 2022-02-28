package com.sdq.worldpeace.hilttest

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondActivity:AppCompatActivity() {
    @Inject
    lateinit var user: User

    private val viewModel:SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("TAG","user = $user")
        Log.d("TAG","viewmodel user = ${viewModel.user}")
    }

}