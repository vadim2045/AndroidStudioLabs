package com.example.lab_8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_8.databinding.CorporationDetailsBinding

class CorporationDetailsActivity : AppCompatActivity() {
    private lateinit var binding: CorporationDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CorporationDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val corporationName = intent.getStringExtra("corporationName")
        binding.corporationDetailsButton.text = corporationName
    }
}
