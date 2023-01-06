package com.sdq.biz.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.sql.Connection

class SecondFragment : Fragment() {
    companion object {
        val dbConnectionLocal = object : ThreadLocal<Connection>() {

/*            @Override
            protected Connection initialValue() {
                try {
                    return DriverManager.getConnection("", "", "");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }*/
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("songdongqi", "fragment 2 onCreate is invoke!!!")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("songdongqi", "fragment 2 onSaveInstanceState is invoke!!!")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("songdongqi", "fragment 2 onViewStateRestored is invoke!!!")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("songdongqi", "fragment 2 onCreateView is invoke!!!")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("songdongqi", "fragment 2 onViewCreated is invoke!!!")
    }

    override fun onStart() {
        super.onStart()
        Log.d("songdongqi", "fragment 2 onStart is invoke!!!")
    }

    override fun onResume() {
        super.onResume()
        Log.d("songdongqi", "fragment 2 onResume is invoke!!!")
    }

    override fun onPause() {
        super.onPause()
        Log.d("songdongqi", "fragment 2 onPause is invoke!!!")
    }

    override fun onStop() {
        super.onStop()
        Log.d("songdongqi", "fragment 2 onStop is invoke!!!")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("songdongqi", "fragment 2 onDestroyView is invoke!!!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("songdongqi", "fragment 2 onDestroy is invoke!!!")
    }
}