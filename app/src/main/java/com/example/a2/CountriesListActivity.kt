package com.example.a2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a2.CountriesSearchActivity.Companion.COUNTRY_NAME
import com.example.a2.databinding.ActivityCountriesListBinding

class CountriesListActivity : AppCompatActivity() {
    private lateinit var bindingListActivity: ActivityCountriesListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingListActivity = ActivityCountriesListBinding.inflate(layoutInflater)
        setContentView(bindingListActivity.root)

        val toastText = intent.getStringExtra(COUNTRY_NAME)
        if (!toastText.isNullOrEmpty()){
            Toast.makeText(this, toastText, Toast.LENGTH_LONG).show()
        }
    }
}