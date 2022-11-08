package com.sdq.biz.mycompose.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sdq.biz.mycompose.ui.theme.WorldPeaceTheme
import kotlinx.coroutines.launch

/**
 *
 * @PackageName com.sdq.biz.mycompose
 * @date 2022/8/31 13:43
 * @author songdongqi
 */
class ScaffoldActivity : ComponentActivity() {
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
            ScaffoldDemo()
        }
    }

    @Composable
    fun ScaffoldDemo() {
        val scaffoldSate = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldSate,
            drawerContent = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "抽屉组件中的内容")
                }
            },
            //标题栏
            topBar = {
                TopAppBar(
                    title = { Text(text = "脚手架示例") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { scaffoldSate.drawerState.open() }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = null
                            )
                        }
                    }
                )
            },
            snackbarHost = {
              SnackbarHost(hostState = it) {data->
                  Snackbar(
                      snackbarData = data,
                      backgroundColor = Color.Red,
                      contentColor = Color.White,
                      shape = CutCornerShape(10.dp)
                  )
              }
            },
            //
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text(text = "悬浮按钮") },
                    onClick = {
                        scope.launch {
                            scaffoldSate.snackbarHostState.showSnackbar("点击了悬浮按钮")
                        }
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            //
            content = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "屏幕内容区域")
                }
            }
        )
    }
}