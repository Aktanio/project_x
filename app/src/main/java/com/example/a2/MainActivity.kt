package com.example.a2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.a2.databinding.ActivityMainBinding
import com.example.a2.fragments.MainFragment
import com.example.a2.viewModel.MainActivityViewModel


@SuppressLint("UseSwitchCompatOrMaterialCode")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewmodel: MainActivityViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel.isMainFragmentOpened.observe(this) {isMainFragmentOpened->
            if (isMainFragmentOpened) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainFragmentContainer, MainFragment())
                    .commit()

                viewmodel.onMainFragmentOpened()
            }
        }
    }
}