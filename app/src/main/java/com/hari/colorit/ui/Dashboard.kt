package com.hari.colorit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.hari.colorit.BuildConfig
import com.hari.colorit.R
import com.hari.colorit.adapters.GridAdapter
import com.hari.colorit.databinding.ActivityDashboardBinding
import java.util.*
import kotlin.collections.ArrayList

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(view.root)

        val isDebug = BuildConfig.DEBUG

        val gridList = if(isDebug){

            arrayListOf(R.color.red, R.color.red, R.color.red, R.color.green, R.color.green, R.color.green,
                R.color.blue, R.color.blue, R.color.blue)
        }else arrayListOf(R.color.red, R.color.red, R.color.red, R.color.red, R.color.green, R.color.green,
            R.color.green, R.color.green, R.color.blue, R.color.blue, R.color.blue, R.color.blue, R.color.yellow,
            R.color.yellow, R.color.yellow, R.color.yellow)

        view.recyclerDashboard.layoutManager = GridLayoutManager(this, if(isDebug) 3 else 4)
        val gridAdapter = GridAdapter(gridList)
        view.recyclerDashboard.adapter = gridAdapter
        view.scrambleBtnDashboard.setOnClickListener{
            Collections.shuffle(gridList)
            gridAdapter.updateGridItems(gridList)
        }
    }
}