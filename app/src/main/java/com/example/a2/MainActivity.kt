package com.example.a2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


@SuppressLint("UseSwitchCompatOrMaterialCode")
class MainActivity : AppCompatActivity() {

    private var pauseTime: Long = 0
    private var resumeTime: Long = 0
    private var isPaused: Boolean = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPause() {
        super.onPause()
        pauseTime = System.currentTimeMillis()
        isPaused = true
    }

    override fun onResume() {
        super.onResume()
        if (isPaused){
            resumeTime = System.currentTimeMillis()

            val seconds = (resumeTime - pauseTime) / 1000

            Toast.makeText(this, "Приложение было свернуто на $seconds секунд", Toast.LENGTH_SHORT).show()

            isPaused = false
        }
    }
}