package com.sdq.biz.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sdq.biz.mycompose.ui.theme.WorldPeaceTheme

/**
 *
 * @PackageName com.sdq.biz.mycompose
 * @date 2022/11/8 13:33
 * @author songdongqi
 */
class BasicTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewComposable()
        }
    }

    @Preview
    @Composable
    fun PreviewComposable() {
        WorldPeaceTheme {
            Demo()
        }
    }

    @Composable
    fun Demo() {

    }
}