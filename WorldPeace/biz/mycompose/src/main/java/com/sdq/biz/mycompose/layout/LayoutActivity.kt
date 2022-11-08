package com.sdq.biz.mycompose.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sdq.biz.mycompose.R
import com.sdq.biz.mycompose.ui.theme.WorldPeaceTheme

/**
 *
 * @PackageName com.sdq.biz.mycompose.layout
 * @date 2022/11/8 13:32
 * @author songdongqi
 */
class LayoutActivity : ComponentActivity() {
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
            Box(
                contentAlignment = Alignment.Center
            ) {
                ScaffoldDemo()
                Column() {
                    BoxDemo()
                    MyBottomNavigation()
                }
            }
        }
    }

    @Composable
    fun BoxDemo() {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Red)
            )
            Text(
                text = "世界"
            )
        }
    }

    @Composable
    fun ScaffoldDemo() {
        var selectedItem by remember { mutableStateOf(0) }
        val items = listOf("主页", "我喜欢的", "设置")
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("主页")
                    },
                    navigationIcon = {
                        IconButton(onClick = {

                        }) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavigation {
                    items.forEachIndexed { index, item ->
                        BottomNavigationItem(
                            icon = {
                                when(index){
                                    0 -> Icon(Icons.Filled.Home, contentDescription = null)
                                    1 -> Icon(Icons.Filled.Favorite, contentDescription = null)
                                    else -> Icon(Icons.Filled.Settings, contentDescription = null)
                                }
                            },
                            label = { Text(item) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                }
            }
        ) {

        }
    }

    @Composable
    fun MyBottomNavigation() {

        var selectedItem by remember{ mutableStateOf(0) }

        BottomNavigation(
            backgroundColor = Color.White
        ) {
            for(index in 0..2 ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .clickable(
                            onClick = {
                                selectedItem = index
                            },
                            indication = null,
                            interactionSource = MutableInteractionSource()
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NavigationIcon(index, selectedItem)
                    Spacer(Modifier.padding(top = 2.dp))
                    AnimatedVisibility(visible = index == selectedItem) {
                        Surface(shape = CircleShape, modifier = Modifier.size(5.dp),color = Color(0xFF252527)) { }
                    }
                }
            }
        }
    }

    @Composable
    fun NavigationIcon(
        index:Int,
        selectedItem:Int
    ){
        val alpha = if (selectedItem != index ) 0.5f else 1f
        LocalIndication
        CompositionLocalProvider(LocalContentAlpha provides alpha) {
            when(index){
                0 -> Icon(Icons.Filled.Home, contentDescription = null)
                1 -> Icon(Icons.Filled.Favorite, contentDescription = null)
                else -> Icon(Icons.Filled.Settings, contentDescription = null)
            }
        }
    }

    @Composable
    fun Demo() {

    }
}