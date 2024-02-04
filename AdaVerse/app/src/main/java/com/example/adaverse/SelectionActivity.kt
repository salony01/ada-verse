package com.example.adaverse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import com.example.adaverse.databinding.ActivitySelectionBinding

class SelectionActivity : AppCompatActivity(),MyInterface{

    private lateinit var binding: ActivitySelectionBinding
    var username: String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        username = intent.getStringExtra("username")
        binding.ottImg.setOnClickListener {
            val intent = Intent(this,SelectionActivity2::class.java).also {
                it.putExtra("Type","ott")
                it.putExtra("username",username)
                startActivity(it)
            }
        }
        binding.liveImg.setOnClickListener {
            var dialog=FragmentLiveTv()
            dialog.show(supportFragmentManager,"customDialog")
        }
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    override fun transferredMessage(msg: String) {
        val intent = Intent(this,LiveTvActivity::class.java).also {
            it.putExtra("genre",msg)
            it.putExtra("username",username)
            startActivity(it)
    }

}}