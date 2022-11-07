package com.sdq.biz.mycompose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 *
 * @PackageName com.sdq.biz.mycompose
 * @date 2022/8/31 13:43
 * @author songdongqi
 */
class MainActivity01 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("Android","Jetpack Compose"))
        }
    }

    @Composable
    fun MessageCard(msg: Message) {
        Text(text = msg.author)
        Text(text = msg.body)
    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
        MessageCard(Message("Android","Jetpack Compose"))
    }
}