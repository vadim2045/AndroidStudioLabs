package com.example.lab_8

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab_8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.recyclerViewVert.layoutManager = LinearLayoutManager(this)
        val corporations = listOf(
            Pair("google", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/640px-Google_2015_logo.svg.png"),
            Pair("meta", "https://admin.itsnicethat.com/images/vhN_fQRZHeKohK2ywvDSL_8Pnzs=/217006/format-webp%7Cwidth-1440/logo-Meta.png"),
            Pair("apple", "https://kyo.md/images/feature_variant/11/Apple-Logo.png"),
            Pair("microsoft", "https://blogs.microsoft.com/wp-content/uploads/prod/2012/08/8867.Microsoft_5F00_Logo_2D00_for_2D00_screen.jpg"),
        )

        val adapter = CorporationAdapter(corporations){ position: Int ->
            val intent = Intent(this, CorporationDetailsActivity::class.java)
            intent.putExtra("corporationName", corporations[position].first)
            startActivity(intent)
        }

        binding.recyclerViewVert.adapter = adapter
        binding.recyclerViewHor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val corporationNames = listOf(
            "google",
            "meta",
            "apple",
            "microsoft",
        )
        val adapterSecond = HorizontalViewAdapter(corporationNames)
        binding.recyclerViewHor.adapter = adapterSecond
    }
}