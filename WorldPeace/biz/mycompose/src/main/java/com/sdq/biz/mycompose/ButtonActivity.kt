package com.sdq.biz.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 *
 * @PackageName com.sdq.biz.mycompose
 * @date 2022/8/31 13:43
 * @author songdongqi
 */
class ButtonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewModel()
        }
    }

    @Preview
    @Composable
    fun PreviewModel() {
        ButtonDemo()
    }

    @Composable
    fun ButtonDemo() {
        Column() {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "确认")
            }
            Button(onClick = { /*TODO*/ }) {
                Icon(
                    // Material 库中的图标，有 Filled, Outlined, Rounded, Sharp, Two Tone 等
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                // 添加间隔
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("喜欢")
            }
            // 获取按钮的状态
            val interactionState = remember { MutableInteractionSource() }

            // 使用 Kotlin 的解构方法
            val (text, textColor, buttonColor) = when {
                interactionState.collectIsPressedAsState().value -> ButtonState(
                    "Just Pressed",
                    Color.Red,
                    Color.Black
                )
                else -> ButtonState("Just Button", Color.White, Color.Red)
            }

            Button(
                onClick = { /*TODO*/ },
                interactionSource = interactionState,
                elevation = null,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor,
                ),
                modifier = Modifier.width(IntrinsicSize.Min).height(IntrinsicSize.Min)
            ) {
                Text(
                    text = text, color = textColor
                )
            }
        }
    }
}

data class ButtonState(
    val text: String,
    val textColor: Color,
    val buttonColor: Color
)