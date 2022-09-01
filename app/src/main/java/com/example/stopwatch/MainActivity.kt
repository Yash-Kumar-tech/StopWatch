package com.example.stopwatch

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import java.time.Clock

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomnavview = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomnavview.selectedItemId = R.id.Clock
        startActivity(Intent(applicationContext, ClockActivity::class.java))
        overridePendingTransition(0, 0)
        bottomnavview.setOnItemSelectedListener {
            if(it.itemId == R.id.Clock) {
                startActivity(Intent(applicationContext, ClockActivity::class.java))
                true
            }
            if(it.itemId == R.id.Timer) {
                startActivity(Intent(applicationContext, TimerActivity::class.java))
                true
            }
            if(it.itemId == R.id.Stopwatch) {
                startActivity(Intent(applicationContext, Stopwatch::class.java))
                true
            }
            false
        }
    }
}