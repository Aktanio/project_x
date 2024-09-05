package com.example.a2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


@SuppressLint("UseSwitchCompatOrMaterialCode")
class MainActivity : AppCompatActivity() {

    private var pauseTime: Long = 0
    private var isPaused: Boolean = false

    companion object {
        const val COUNTRY_NAME = "COUNTRY_NAME"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countryName = findViewById<EditText>(R.id.countryName)
        val searchButton = findViewById<Button>(R.id.searchButton)

        searchButton.setOnClickListener {
            val countryToast = countryName.text.toString()
            val intent = Intent(this, CountriesListActivity::class.java)
            intent.putExtra(COUNTRY_NAME, countryToast)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        pauseTime = System.currentTimeMillis()
        isPaused = true
    }

    override fun onResume() {
        super.onResume()
        if (isPaused){
            val resumeTime = System.currentTimeMillis()

            val seconds = (resumeTime - pauseTime) / 1000

            Toast.makeText(this, "Приложение было свернуто на $seconds секунд", Toast.LENGTH_SHORT).show()

            isPaused = false
        }
    }
}