package com.sdq.biz.mycompose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.CombinedModifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.input.pointer.PointerInputModifier
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.OnRemeasuredModifier
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.modifier.ModifierLocal
import androidx.compose.ui.semantics.SemanticsModifier

/**
 *
 * @PackageName com.sdq.biz.mycompose.basic
 * @date 2022/11/9 14:08
 * @author songdongqi
 */
class ModifierActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Composable
    fun Demo() {
//        LayoutModifier
//        DrawModifier
//        PointerInputModifier
//        ParentDataModifier
//        SemanticsModifier
//        OnRemeasuredModifier
//        OnPlacedModfier
//        ModifierLocal
//        Text(
//            text = "",
//            modifier = CombinedModifier()
//                .composed {
//                }
//        )
    }
}