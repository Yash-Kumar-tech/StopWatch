package com.example.stopwatch

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.TextClock
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ClockActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_clock)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.Clock
        bottomNavigationView.setOnItemSelectedListener {
            if(it.itemId == R.id.Stopwatch) {
                startActivity(Intent(applicationContext, Stopwatch::class.java))
                true
            }
            if (it.itemId == R.id.Timer) {
                startActivity(Intent(applicationContext, TimerActivity::class.java))
                true
            }
            false
        }
    }
}