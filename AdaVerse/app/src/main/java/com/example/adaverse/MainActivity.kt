package com.example.adaverse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adaverse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.loginBtn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}