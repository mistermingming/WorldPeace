package com.sdq.libs.base.kotlin

import android.net.Uri
import org.junit.Test

/**
 *
 * @PackageName com.sdq.libs.base.kotlin
 * @date 2022/12/12 13:28
 * @author songdongqi
 */
class UriTest {
    @Test
    fun appendTest() {
        val originUrl = "https://qsdf.gw.com.cn/xgxz/detail.html?channel=jjsg&code="
        val uriBuilder = Uri.parse(originUrl).buildUpon()
        println(originUrl.)

        val queryParameters = mapOf(
            "title" to "F10",
            "code" to "787001"
        )
        queryParameters.forEach {
            uriBuilder.appendQueryParameter(it.key, it.value)
        }
        val url = uriBuilder.build().toString()
        println("$url")
    }
}