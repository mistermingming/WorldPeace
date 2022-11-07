package com.sdq.biz.mycompose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sdq.biz.mycompose.R

/**
 *
 * @PackageName com.sdq.biz.mycompose
 * @date 2022/8/31 13:43
 * @author songdongqi
 */
class MainActivity02 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("Android", "Jetpack Compose"))
        }
    }

    @Composable
    fun MessageCard(msg: Message) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription =null
            )
            Column {
                Text(text = msg.author)
                Text(text = msg.body)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
        MessageCard(Message("Android", "Jetpack Compose"))
    }
}