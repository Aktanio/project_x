package com.example.a2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a2.MainActivity.Companion.COUNTRY_NAME

class CountriesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries_list)

        val toast = intent.getStringExtra(COUNTRY_NAME)
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show()
    }
}