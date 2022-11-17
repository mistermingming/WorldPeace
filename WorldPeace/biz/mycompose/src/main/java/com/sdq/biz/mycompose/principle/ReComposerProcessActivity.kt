package com.sdq.biz.mycompose.principle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sdq.biz.mycompose.ui.theme.WorldPeaceTheme

/**
 *
 * @PackageName com.sdq.biz.mycompose.principle
 * @date 2022/11/17 14:42
 * @author songdongqi
 */
class ReComposerProcessActivity : ComponentActivity() {
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
    var display by mutableStateOf("Init")
    @Composable
    fun Demo() {
        Text (
            text = display,
            fontSize = 50.sp,
            modifier = Modifier.clickable {
                display = "change" // recompose不能执行到，此时是 GlobalSnapshot
            }
        )
        display = "change" // recompose能够执行到，此时是 MutableSnapShot
    }
}