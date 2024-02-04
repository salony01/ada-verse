package com.example.adaverse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adaverse.adapter.GenreAdapter
import com.example.adaverse.adapter.LiveTvAdapter
import com.example.adaverse.databinding.ActivityLiveTvBinding
import com.example.adaverse.model.LiveTvModelResponse
import com.example.adaverse.model.LoginModel
import com.example.adaverse.model.ottModel
import com.example.adaverse.model.ottResponseModel
import retrofit2.Call
import retrofit2.Response

class LiveTvActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLiveTvBinding
    private lateinit var liveTvModelResponse: LiveTvModelResponse
    private lateinit var ladapter: LiveTvAdapter
    var genre: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLiveTvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        genre=intent.getStringExtra("genre")



        val retrofit= ServiceBuilder.buildService(APIInterface::class.java)
        val obj= LoginModel(genre!!)
        retrofit.liveTv(obj).enqueue(
            object:retrofit2.Callback<LiveTvModelResponse>{
                override fun onResponse(
                    call: Call<LiveTvModelResponse>,
                    response: Response<LiveTvModelResponse>
                ) {
                    liveTvModelResponse=response.body()!!
                    binding.recyclerView.apply {
                        ladapter=LiveTvAdapter(liveTvModelResponse,this@LiveTvActivity)
                        adapter=ladapter
                        layoutManager=LinearLayoutManager(this@LiveTvActivity)
                    }
                }

                override fun onFailure(call: Call<LiveTvModelResponse>, t: Throwable) {

                }


            })
    }
}