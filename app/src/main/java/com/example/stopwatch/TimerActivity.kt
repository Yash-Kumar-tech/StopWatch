package com.example.stopwatch

import android.content.Intent
import android.os.*
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class TimerActivity : AppCompatActivity() {
    private var countdown = "000000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.Timer
        bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.Clock) {
                startActivity(Intent(applicationContext, ClockActivity::class.java))
                true
            }
            if(it.itemId == R.id.Stopwatch) {
                startActivity(Intent(applicationContext, Stopwatch::class.java))
                true
            }
            false
        }
    }

    fun timerstart(view: View) {
        val timerView = findViewById<TextView>(R.id.timerValue)
        var hr = timerView.text.toString().substring(0,2).toLong()
        var min = timerView.text.toString().substring(4,6).toInt()
        var sec = timerView.text.toString().substring(8, 10).toInt()
        if(sec >= 60) {
            min += (sec/60)
            sec %= 60
        }
        if(min >= 60) {
            if(hr + (min/60) <= 99) {
                hr += (min/60)
            }
            min %= 60
        }
        var txt = ""
        if(hr < 10) txt += "0$hr"
        else        txt += hr
        txt += "h:"
        if(min < 10)    txt += "0$min"
        else            txt += min
        txt += "m:"
        if(sec < 10)    txt += "0$sec"
        else            txt += sec
        txt += "s"
        timerView.text = txt
        object: CountDownTimer(((hr * 3600 + min * 60 + sec) * 1000).toLong(), 1000) {
            override fun onTick(p0: Long) {
                var time_remaining = (p0/1000).toInt()
                sec = time_remaining % 60
                min = (time_remaining / 60) % 60
                hr = (p0/1000) / 3600
                (""+hr+"h:"+min+"m:"+sec+"s").also { timerView.text = it }
            }

            override fun onFinish() {
                Toast.makeText(applicationContext, "Time's Up!", Toast.LENGTH_SHORT).show()
                countdown = "000000"
                val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(2500, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    @Suppress("DEPRECATION")
                    vibrator.vibrate(2500)
                }
            }
        }.start()
    }

    fun numclick(view: View) {
        val btn1 = findViewById<Button>(view.id)
        val txt = btn1.text
        val timerView = findViewById<TextView>(R.id.timerValue)
        countdown = countdown.substring(1) + txt
        (countdown.substring(0, 2) + "h:" + countdown.substring(2, 4) +
                "m:"+countdown.substring(4) + "s").also { timerView.text = it }
    }

    fun clearclick(view: View) {
        countdown = "000000"
        val timerView = findViewById<TextView>(R.id.timerValue)
        timerView.text = "00h:00m:00s"
    }
    fun backspaceclick(view: View) {
        val timerView = findViewById<TextView>(R.id.timerValue)
        countdown = "0" + countdown.substring(0, 5)
        (countdown.substring(0, 2) + "h:" + countdown.substring(2, 4) +
                "m:"+countdown.substring(4) + "s").also { timerView.text = it }
    }
}