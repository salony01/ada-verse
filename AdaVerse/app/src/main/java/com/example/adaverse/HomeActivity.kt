package com.example.adaverse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adaverse.adapter.GenreAdapter
import com.example.adaverse.databinding.ActivityHomeBinding
import com.example.adaverse.model.ottModel
import com.example.adaverse.model.ottResponseModel
import retrofit2.Call
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    var type : String?= null
    var showType: String?=null
    var genre: String?= null
    var username: String?=null
    private lateinit var genreadapter: GenreAdapter
    private lateinit var contentList: ottResponseModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type=intent.getStringExtra("type")
        showType=intent.getStringExtra("showType")
        genre=intent.getStringExtra("genre")
        username=intent.getStringExtra("username")

        val retrofit= ServiceBuilder.buildService(APIInterface::class.java)
        val obj= ottModel(type,showType, genre!!)

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

        retrofit.requestShows(obj).enqueue(
            object:retrofit2.Callback<ottResponseModel>{
                override fun onResponse(
                    call: Call<ottResponseModel>,
                    response: Response<ottResponseModel>
                ) {

                    Log.d("TAG",response.body().toString())
                    contentList= response.body()!!
                    binding.recyclerView.apply {
                        genreadapter= GenreAdapter(contentList.ott_recommendations,this@HomeActivity,null,
                            username.toString()
                        )
                        adapter=genreadapter
                        layoutManager= LinearLayoutManager(this@HomeActivity)

                    }
                }

                override fun onFailure(call: Call<ottResponseModel>, t: Throwable) {
                    Log.d("TAG","failed"+t)
                }

            }

        )

        binding.searchGenre.setOnClickListener {
            val intent = Intent(this,SelectionActivity::class.java).also {
                it.putExtra("username",username)
                startActivity(it)
            }
        }
    }
}