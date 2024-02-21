package ru.com.vbulat.servicetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.com.vbulat.servicetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}