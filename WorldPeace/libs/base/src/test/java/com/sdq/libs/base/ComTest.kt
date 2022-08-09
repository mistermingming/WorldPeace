package com.sdq.libs.base

import java.sql.Connection

class ComTest {
    companion object {
        val dbConnectionLocal = object : ThreadLocal<Connection>() {
            override fun initialValue(): Connection? {
                return super.initialValue()
            }
        }
    }
}