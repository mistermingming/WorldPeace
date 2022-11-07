package com.sdq.biz.mycompose.basic

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdq.biz.mycompose.R
import com.sdq.biz.mycompose.ui.theme.WorldPeaceTheme

/**
 *
 * @PackageName com.sdq.biz.mycompose
 * @date 2022/8/31 13:43
 * @author songdongqi
 */
class WidgetActivity : ComponentActivity() {
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
            Column {
                SliderDemo()
                TextDemo()
                TextFieldDemo()
                BasicFieldDemo()
            }
        }
    }

    @Composable
    fun SliderDemo() {
        var progress by remember { mutableStateOf(0f) }
        Slider(
            value = progress,
            colors = SliderDefaults.colors(
                thumbColor = Color.White, // 圆圈的颜色
                activeTrackColor = Color(0xFF0079D3)
            ),
            onValueChange = {
                progress = it
            },
        )
    }

    @Composable
    fun TextDemo() {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "你好呀陌生人，这是一个标题",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "你好呀陌生人，我是内容",
                style = MaterialTheme.typography.body2
            )
            Text(
                text = "你好陌生人",
                style = TextStyle(
                    fontWeight = FontWeight.W900, //设置字体粗细
                    fontSize = 20.sp,
                    letterSpacing = 7.sp
                )
            )
            Text(
                text = "你好呀陌生人，这是一个标题，不是很长，因为我想不出其他什么比较好的标题了",
                style = MaterialTheme.typography.h6,
                maxLines = 1,
            )
            Text(
                text = "你好呀陌生人，这是一个标题，不是很长，因为我想不出其他什么比较好的标题了",
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "每天摸鱼",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left
            )
            Text(
                text = "这好吗",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "这非常的好",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right
            )
            Text(
                text = "两面包夹芝士".repeat(5),
            )
            Spacer(Modifier.padding(vertical = 15.dp))
            Text(
                text = "两面包夹芝士".repeat(5),
                lineHeight = 30.sp
            )

            // 获取 context
            val context = LocalContext.current
            Text(
                text = "确认编辑",
                modifier = Modifier.clickable(
                    onClick = {
                        // 通知事件
                        Toast.makeText(context, "你点击了此文本", Toast.LENGTH_LONG).show()
                    },
                    indication = null,
                    interactionSource = MutableInteractionSource()
                )
            )

            Text(
                buildAnnotatedString {
                    append("你现在观看的章节是 ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                        append("Text")
                    }
                }
            )

            val text = buildAnnotatedString {
                append("勾选即代表同意")
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF0E9FF2),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("用户协议")
                }
            }
            ClickableText(
                text = text,
                onClick = { offset ->
                    Toast.makeText(context, "Hi，你按到了第 $offset 位的文字", Toast.LENGTH_LONG).show()
                }
            )

            val annotatedText = buildAnnotatedString {
                append("勾选即代表同意")
                pushStringAnnotation(
                    tag = "tag",
                    annotation = "一个用户协议啦啦啦，内容内容"
                )
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF0E9FF2),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("用户协议")
                }
                pop()
            }
            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    annotatedText.getStringAnnotations(
                        tag = "tag",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let { annotation ->
                        Toast.makeText(context, "你已经点到 ${annotation.item} 啦", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            )

            // 将内部 Text 组件的 alpha 强调程度设置为高
            // 注意: MaterialTheme 已经默认将强调程度设置为 high
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                Text("这里是high强调效果")
            }
            // 将内部 Text 组件的 alpha 强调程度设置为中
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("这里是medium强调效果")
            }
            // 将内部 Text 组件的 alpha 强调程度设置为禁用
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                Text("这里是禁用后的效果")
            }
        }
    }

    @Composable
    fun TextFieldDemo() {
        var text by remember { mutableStateOf("") }
        var passwordHidden by remember { mutableStateOf(false) }

        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordHidden = !passwordHidden
                    }
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_launcher_foreground),
                        null,
                        modifier = Modifier.size(50.dp)
                    )
                }
            },
            label = {
                Text("密码")
            },
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None
        )
    }

    @Composable
    fun BasicFieldDemo() {
        var text by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD3D3D3)),
            contentAlignment = Alignment.Center
        ) {
            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier
                    .background(Color.White, CircleShape)
                    .height(35.dp)
                    .fillMaxWidth(),
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        IconButton(
                            onClick = { }
                        ) {
                            Icon(Icons.Filled.Favorite, null)
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            innerTextField()
                        }
                        IconButton(
                            onClick = { },
                        ) {
                            Icon(Icons.Filled.Send, null)
                        }
                    }
                }
            )
        }
    }
}