package com.sdq.biz.mycompose.basic

import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sdq.biz.mycompose.ui.theme.WorldPeaceTheme

/**
 *
 * @PackageName com.sdq.biz.mycompose
 * @date 2022/8/31 13:43
 * @author songdongqi
 */
class IconActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewMessageCard()
        }
    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
        WorldPeaceTheme() {
            IconButtonDemo()
        }
    }

    @Composable
    fun IconButtonDemo() {
        Column() {
            Row{
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Search, null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Done, null)
                }
            }

            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color.Red
            )
        }
    }
}