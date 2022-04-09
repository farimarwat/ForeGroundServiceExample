package com.marwatsoft.foregroundserviceexample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.marwatsoft.foregroundserviceexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mContext = this
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            ContextCompat.startForegroundService(mContext,Intent(mContext,MyService::class.java))
        }
        binding.btnStop.setOnClickListener {
            stopService(Intent(mContext,MyService::class.java))
        }
    }
}