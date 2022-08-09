package com.sdq.biz.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.sql.Connection

class HomeFragment:Fragment() {
    companion object{
        val dbConnectionLocal = object :ThreadLocal<Connection>() {

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}