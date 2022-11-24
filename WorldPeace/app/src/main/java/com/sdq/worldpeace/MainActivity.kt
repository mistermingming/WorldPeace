package com.sdq.worldpeace

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.sdq.worldpeace.hilttest.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var user1: User

//    @Inject
//    lateinit var user2: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView
        setContentView(R.layout.activity_main)
        findViewById<EditText>(R.id.et_test).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val money = s?.trim()?.toString() ?: ""
                Log.d("TAG", "edittext = ${s?.trim()?.toString() ?: ""}")
                Log.d("TAG", "edittext = ${money.toDoubleOrNull()}")

            }
        })
//        startActivity(Intent(this, SecondActivity::class.java))
        Log.d("TAG", "user1 =$user1")
//        Log.d("TAG", "user2 =$user2")

    }
}