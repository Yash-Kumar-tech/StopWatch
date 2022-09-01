package com.example.stopwatch

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.stopwatch.databinding.ActivityStopwatchBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.String
import java.util.*
import java.util.logging.Handler


class Stopwatch : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityStopwatchBinding
    private var seconds: Long = 0
    private var running = false
    private var wasRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_stopwatch)

        val bottomnavview = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomnavview.selectedItemId = R.id.Stopwatch
        bottomnavview.setOnItemSelectedListener {
            if(it.itemId == R.id.Clock) {
                startActivity(Intent(applicationContext, ClockActivity::class.java))
                true
            }
            if(it.itemId == R.id.Timer) {
                startActivity(Intent(applicationContext, TimerActivity::class.java))
                true
            }
            false
        }

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getLong("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState
            .putLong("seconds", seconds)
        savedInstanceState
            .putBoolean("running", running)
        savedInstanceState
            .putBoolean("wasRunning", wasRunning)
    }
    fun resetclick(view: View) {
        seconds = 0
        running = false
        val timeView = findViewById<TextView>(R.id.stopwatchtimer)
        timeView.text = "00:00:000"
    }
    fun startclick(view: View) {
        running = true
    }
    fun pauseclick(view: View) {
        running = false
    }
    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }
    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }
    private fun runTimer() {
        val timeView = findViewById<TextView>(R.id.stopwatchtimer)
        val handlertime = android.os.Handler()
        var minutes: Int
        var secs: Int
        handlertime.post(object : Runnable {
            override fun run() {
                val ms = (seconds % 100).toInt()
                secs = ((seconds / 100) % 60).toInt()
                minutes = (seconds / 6000).toInt()

                if (running) {
                    val time =
                        String.format(Locale.getDefault(), "%02d:%02d:%02d", minutes, secs, ms)
                    timeView.text = time
                    seconds += 11
                }
                handlertime.postDelayed(this, 11)
            }
        })

        /*handlerMs.post(object : Runnable {
            override fun run() {
                if (seconds >= 99) {
                    seconds = 0
                }

                // if running increment the ms
                if (running) {
                    val msString = String.format(Locale.getDefault(), "%02d", seconds)
                    timeView.text = msString
                    seconds++
                }
                handlerMs.postDelayed(this, 1)
            }
        })*/
    }
}