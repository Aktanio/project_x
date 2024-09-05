package com.example.a2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a2.databinding.ActivityCountriesSearchBinding

class CountriesSearchActivity : AppCompatActivity() {
    private var pauseTime: Long = 0
    private var isPaused: Boolean = false

    private lateinit var bindingActivitySearch: ActivityCountriesSearchBinding

    companion object {
        const val COUNTRY_NAME = "COUNTRY_NAME"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivitySearch = ActivityCountriesSearchBinding.inflate(layoutInflater)
        setContentView(bindingActivitySearch.root)

        bindingActivitySearch.searchButton.setOnClickListener {
            val countryToast = bindingActivitySearch.countryName.text.toString()
            val intent = Intent(this,CountriesListActivity::class.java)
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