package com.sdq.biz.mycompose.principle

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.sdq.biz.mycompose.ui.theme.WorldPeaceTheme

/**
 *
 * @PackageName com.sdq.biz.mycompose.principle
 * @date 2022/11/15 10:14
 * @author songdongqi
 * Just don't rely on side effects from recomposition and compose will do the right thing -- Compose Team
 */
class RecomScopeActivity : ComponentActivity() {
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
            Foo02()
        }
    }

    @Composable
    fun Foo() {
        var text by remember { mutableStateOf("") }
        Log.d("TAG", "Foo")
        Button(
            onClick = {
                text = "$text $text"
            }.also { Log.d("TAG", "Button") }
        ) {
            Log.d("TAG", "Button content lambda")
            Text(text).also { Log.d("TAG", "Text") }
        }
    }

    /**
     * Column、Row、Box 乃至 Layout 这种容器类 Composable 都是 inline 函数，因此它们只能共享调用方的重组
     * 范围，也就是 Button 的 尾lambda
     * D/Compose: Button content lambda
     * D/Compose: Box
     * D/Compose: Text
     */
    @Composable
    fun Foo01() {
        var text by remember { mutableStateOf("") }
        Button(onClick = { text = "$text $text" }) {
            Log.d("TAG", "Button content lambda")
            Box {
                Log.d("TAG", "Box")
                Text(text).also { Log.d("TAG", "Text") }
            }
        }
    }

    /**
     * D/Compose: Text
     */
    @Composable
    fun Foo02() {
        var text by remember { mutableStateOf("") }
        Button(
            onClick = {
                val originUrl = "https://qsdf.gw.com.cn/xgxz/detail.html?channel=jjsg&code="
                val uri = Uri.parse(originUrl)
                println(uri.encodedQuery)
                println(uri.query)
                println(uri.queryParameterNames)
                val uriBuilder = uri.buildUpon()
                uriBuilder.clearQuery()
                val queryParameters = mapOf(
                    "title" to "F10",
                    "code" to "787001"
                )
                queryParameters.forEach {
                    if (uri.queryParameterNames.equals(it.key)){
                        uriBuilder.appendQueryParameter(it.key, it.value)
                    }else{
                        uriBuilder.appendQueryParameter(it.key, it.value)
                    }
                }
                val url = uriBuilder.build().toString()
                println(url)
                text = "$text $text"
            }
        ) {
            Log.d("TAG", "Button content lambda")
            Wrapper {
                Text(text).also { Log.d("TAG", "Text") }
            }
        }
    }

    @Composable
    fun Wrapper(content: @Composable () -> Unit) {
        Log.d("TAG", "Wrapper recomposing")
        Box {
            Log.d("TAG", "Box")
            content()
        }
    }

    /**
     * 你不能预设某个 function/lambda 一定不参与重组，因而在里面侥幸的埋了一些副作用代码，使其变得不纯洁。
     * 因为我们无法确定这里是否存在 “inline陷阱”，即使能确定也不保证现在的优化规则在未来不会改变。

     * 所以最安全的做法是，将副作用写到 LaunchedEffect{}、DisposableEffect{}、SideEffect{} 中，并且
     * 使用 remeber{}、derivedStateOf{} 处理那些耗时的计算。
     */
}