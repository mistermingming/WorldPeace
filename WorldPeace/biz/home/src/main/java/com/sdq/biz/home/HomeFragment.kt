package com.sdq.biz.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.sql.Connection

class HomeFragment : Fragment() {
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
        Log.d("songdongqi", "fragment onCreate is invoke!!!")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("songdongqi", "fragment onSaveInstanceState is invoke!!!")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("songdongqi", "fragment onViewStateRestored is invoke!!!")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("songdongqi", "fragment onCreateView is invoke!!!")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    /**
     * 执行完onViewCreated才会执行 FragmentStateManager.java-> activityCreated ->restoreViewState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("songdongqi", "fragment onViewCreated is invoke!!!")
    }

    override fun onStart() {
        super.onStart()
        Log.d("songdongqi", "fragment onStart is invoke!!!")
    }

    override fun onResume() {
        super.onResume()
        Log.d("songdongqi", "fragment onResume is invoke!!!")
    }

    override fun onPause() {
        super.onPause()
        Log.d("songdongqi", "fragment onPause is invoke!!!")
    }

    override fun onStop() {
        super.onStop()
        Log.d("songdongqi", "fragment onStop is invoke!!!")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("songdongqi", "fragment onDestroyView is invoke!!!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("songdongqi", "fragment onDestroy is invoke!!!")
    }
}