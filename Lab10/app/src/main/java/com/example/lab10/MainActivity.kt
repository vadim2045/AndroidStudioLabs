package com.example.lab10

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lab10.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getComments()

        binding.button.setOnClickListener {
            addComment()
        }
    }

    private fun getComments(){
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launch {
            val response = apiInterface.getComment(1)
            binding.textView.text = response.body()?.name
        }
    }

    private fun addComment(){
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launch {
            try {
                val comment = Comment(
                    id = binding.commentId.text.toString().toInt(),
                    postId = binding.postId.text.toString().toInt(),
                    name = binding.name.text.toString(),
                    email = binding.email.text.toString(),
                    body = binding.body.text.toString()
                )
                val response = apiInterface.addComment(comment)
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(applicationContext, "new comment added, id: ${response.body()?.id}", Toast.LENGTH_SHORT).show()
                }
            } catch (Ex: Exception) {
                Toast.makeText(applicationContext, "could not add comment :(", Toast.LENGTH_SHORT).show()
            }
        }
    }
}