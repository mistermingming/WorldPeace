package com.sdq.biz.mycompose.design

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sdq.biz.mycompose.ui.theme.WorldPeaceTheme

/**
 *
 * @PackageName com.sdq.biz.mycompose.design
 * @date 2022/11/17 16:14
 * @author songdongqi
 */
class GraphicsActivity : ComponentActivity() {
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

    @Composable
    fun Demo() {
        Column {
            DrawColorRing()
            DrawBefore()
            DrawBehind()
            DrawBorder()
        }
    }

    @Composable
    fun DrawColorRing() {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val radius = 300.dp
            val ringWidth = 30.dp
            Canvas(modifier = Modifier.size(radius)) {
                this.drawCircle( // 画圆
                    brush = Brush.sweepGradient(
                        colors = listOf(Color.Red, Color.Green, Color.Red),
                        center = Offset(radius.toPx() / 2f, radius.toPx() / 2f)
                    ),
                    radius = radius.toPx() / 2f,
                    style = Stroke(
                        width = ringWidth.toPx()
                    )
                )
            }
        }
    }

    @Composable
    fun DrawBefore() {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(100.dp)
                    .drawWithContent {
                        drawContent()
                        drawCircle(
                            Color(0xffe7614e),
                            18.dp.toPx() / 2,
                            center = Offset(drawContext.size.width, 0f)
                        )
                    }
            ) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Diana")
            }
        }
    }

    @Composable
    fun DrawBehind() {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(100.dp)
                    .drawBehind {
                        drawContext.canvas
                        drawCircle(
                            Color(0xffe7614e),
                            18.dp.toPx() / 2,
                            center = Offset(drawContext.size.width, 0f)
                        )
                    }
            ) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Diana")
            }
        }
    }

    @Composable
    fun DrawBorder() {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var borderColor by mutableStateOf(Color.Red)
                Card(
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier
                        .size(100.dp)
                        .drawWithCache {
                            println("compose_study 此处不会发生 Recompose")
                            val path = Path().apply {
                                moveTo(0f, 0f)
                                relativeLineTo(100.dp.toPx(), 0f)
                                relativeLineTo(0f, 100.dp.toPx())
                                relativeLineTo(-100.dp.toPx(), 0f)
                                relativeLineTo(0f, -100.dp.toPx())
                            }
                            onDrawWithContent {
                                println("compose_study 此处会发生 Recompose")
                                drawContent()
                                drawPath(
                                    path = path,
                                    color = borderColor,
                                    style = Stroke(
                                        width = 10f,
                                    )
                                )
                            }
                        }
                ) {
                    Icon(imageVector = Icons.Filled.Face, contentDescription = "Diana")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    borderColor = Color.Yellow
                }) {
                    Text("Change Color")
                }
            }
        }
    }
}