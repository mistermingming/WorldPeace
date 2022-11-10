package com.sdq.biz.mycompose.coroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect

/**
 *
 * @PackageName com.sdq.biz.mycompose.coroutine
 * @date 2022/11/9 14:03
 * @author songdongqi
 */
class CoroutineActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Composable
    fun Demo() {
        SideEffect(){

        }
//        DisposableEffect
//        LaunchedEffect
        //rememberCoroutineScope()
        // rememberUpdatedState()
    }
}