package com.sdq.biz.mycompose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sdq.biz.mycompose.R
import com.sdq.biz.mycompose.ui.theme.WorldPeaceTheme

/**
 *
 * @PackageName com.sdq.biz.mycompose
 * @date 2022/8/31 13:43
 * @author songdongqi
 */
class ImageActivity : ComponentActivity() {
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
            ImageDemo()
        }
    }

    @Composable
    fun ImageDemo() {
        Surface(
            shape = CircleShape,
            border = BorderStroke(5.dp, Color.Gray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(350.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}