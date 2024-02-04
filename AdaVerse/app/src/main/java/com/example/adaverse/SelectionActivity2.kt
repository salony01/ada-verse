package com.example.adaverse

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import com.example.adaverse.databinding.ActivitySelection2Binding

class SelectionActivity2 : AppCompatActivity(),MyInterface {
    private lateinit var binding: ActivitySelection2Binding
    var type : String?= null
    var showType: String?=null
    var username: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySelection2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        type= intent.getStringExtra("type")
        username = intent.getStringExtra("username")
        binding.moviesImg.setOnClickListener {
            showType="movie"
            var dialog=Custom_Dialog_fragment()
            dialog.show(supportFragmentManager,"customDialog")
           /*
            }*/
        }

        binding.showsImg.setOnClickListener {
            showType="show"
            var dialog=Custom_Dialog_fragment()
            dialog.show(supportFragmentManager,"customDialog")
        }

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }



    }

    override fun transferredMessage(msg: String) {
        val intent = Intent(this,GenreActivity::class.java).also {
            it.putExtra("Type",type)
            it.putExtra("showType",showType)
            it.putExtra("genre",msg)
            it.putExtra("username",username)
            startActivity(it)
    }


}}