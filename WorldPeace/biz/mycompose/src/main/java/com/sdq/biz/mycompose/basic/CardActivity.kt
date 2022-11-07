package com.sdq.biz.mycompose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sdq.biz.mycompose.ui.theme.WorldPeaceTheme

/**
 *
 * @PackageName com.sdq.biz.mycompose
 * @date 2022/8/31 13:43
 * @author songdongqi
 */
class CardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewCard()
        }
    }

    @Preview
    @Composable
    fun PreviewCard() {
        WorldPeaceTheme() {
            CardDemo()
        }
    }

    @Composable
    fun CardDemo() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp) // 外边距
                .clickable { },

            // 设置点击波纹效果，注意如果 CardDemo() 函数不在 MaterialTheme 下调用
            // 将无法显示波纹效果

            elevation = 10.dp // 设置阴影
        ) {
            Column(
                modifier = Modifier.padding(15.dp) // 内边距
            ) {
                Text(
                    buildAnnotatedString {
                        append("欢迎来到 ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W900,
                                color = Color(0xFF4552B8)
                            )
                        ) {
                            append("Jetpack Compose 博物馆")
                        }
                    }
                )
                Text(
                    buildAnnotatedString {
                        append("你现在观看的章节是 ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                            append("Card")
                        }
                    }
                )
            }
        }
    }
}