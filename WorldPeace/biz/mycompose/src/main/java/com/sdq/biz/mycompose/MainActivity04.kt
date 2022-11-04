package com.sdq.biz.mycompose

import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sdq.biz.mycompose.ui.theme.WorldPeaceTheme
import com.sdq.biz.mycompose.widget.LayoutStudy

/**
 *
 * @PackageName com.sdq.biz.mycompose
 * @date 2022/8/31 13:43
 * @author songdongqi
 */
class MainActivity04 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Looper.getMainLooper().setMessageLogging { msg ->
            Log.v("test", "looper message = $msg")
        }
        setContent {
            PreviewMessageCard()
        }
    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
        WorldPeaceTheme() {
            LayoutStudy()
        }
    }

    @Composable
    fun Demo() {

    }
}