package com.sdq.biz.mycompose.basic

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
class MainActivity05 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewMessageCard()
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewMessageCard() {
        WorldPeaceTheme {
            MessageCard(
                msg = Message("Jetpack Compose 博物馆", "我们开始更新啦")
            )
        }
    }

    @Composable
    fun Counter() {
        var count by remember { mutableStateOf(0) }
        Button(
            onClick = { count += 1 }
        ) {
            Text(text = "Count:$count")
        }
    }

    @Composable
    fun MessageCard(msg: Message) {
        Surface(
            shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
            elevation = 5.dp,
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Row(
                modifier = Modifier.padding(all = 8.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colors.secondary, shape = CircleShape)
                )
                Spacer(Modifier.padding(horizontal = 8.dp))
                Column {
                    Text(
                        text = msg.author,
                        color = MaterialTheme.colors.secondaryVariant,
                        style = MaterialTheme.typography.subtitle2
                    )
                    Spacer(Modifier.padding(vertical = 4.dp))
                    Text(
                        text = msg.body,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}