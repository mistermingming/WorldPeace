package com.sdq.biz.mycompose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
 * @date 2022/11/7 15:26
 * @author songdongqi
 */
class UserAgreementActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewAgreement()
        }
    }

    @Preview
    @Composable
    fun PreviewAgreement() {
        WorldPeaceTheme() {
            AgreementDemo()
        }
    }

    @Composable
    fun AgreementDemo() {
        var content by remember { mutableStateOf("") }
        val openDialog = remember { mutableStateOf(false) }
        val annotatedText = buildAnnotatedString {
            append("勾选即代表同意")
            pushStringAnnotation(
                tag = "tag",
                annotation = " 如果你看到了这个界面，就默认代表你同意我们的所有用户协议"
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    annotatedText.getStringAnnotations(
                        tag = "tag", start = offset,
                        end = offset
                    ).firstOrNull()?.let { annotation ->
                        openDialog.value = true
                        content = annotation.item
                    }
                }
            )
        }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "用户协议",
                            style = MaterialTheme.typography.h6,
                        )
                    }
                },
                text = {
                    Text(content)
                },
                confirmButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text("确认")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text("取消")
                    }
                }
            )
        }
    }
}