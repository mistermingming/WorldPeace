package com.sdq.biz.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        savedStateRegistry.consumeRestoredStateForKey("android:support:fragments")
        super.onCreate(savedInstanceState)
        Log.d("songdongqi","1 onCreate is invoke!!!")
        setContentView(R.layout.activity_home)

        findViewById<TextView>(R.id.tv_hello).setOnClickListener {
            startActivity(Intent(this,SecondActivity::class.java))
        }

        observe()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("songdongqi","1 onSaveInstanceState is invoke!!!")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("songdongqi","1 onRestoreInstanceState is invoke!!!")
    }

    /**
     * Activity从后台重新回到前台时被调用
     */
    override fun onRestart() {
        super.onRestart()
        Log.d("songdongqi","1 onRestart is invoke!!!")
    }

    /**
     * Activity创建或者从后台重新回到前台时被调用
     */
    override fun onStart() {
        super.onStart()
        Log.d("songdongqi","1 onStart is invoke!!!")
    }

    /**
     * Activity创建或者从被覆盖、后台重新回到前台时被调用
     */
    override fun onResume() {
        super.onResume()
        Log.d("songdongqi","1 onResume is invoke!!!")
    }

    /**
     * Activity被覆盖到下面或者锁屏时被调用
     */
    override fun onPause() {
        super.onPause()
        Log.d("songdongqi","1 onPause is invoke!!!")
    }

    /**
     * 退出当前Activity或者跳转到新Activity时被调用
     */
    override fun onStop() {
        super.onStop()
        Log.d("songdongqi","1 onStop is invoke!!!")
    }

    /**
     * 退出当前Activity时被调用,调用之后Activity就结束了
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("songdongqi","1 onDestroy is invoke!!!")
    }

    private fun observe(){
        lifecycleScope.launch {
            flow<Int>{
                emit(1)
            }
                .flowWithLifecycle(lifecycle)
                .distinctUntilChanged()
                .collect{
                    Log.d("songdongqi","collect $it")
                }
        }
    }

    private fun simpleFlow5() = flow<Int> {
        for (i in 1..5) {
            delay(100)
            emit(i)
            println("emitting $i ${Thread.currentThread().name}")
        }
    }
}