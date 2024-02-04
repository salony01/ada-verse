package com.example.adaverse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adaverse.adapter.GenreAdapter
import com.example.adaverse.databinding.ActivityGenreBinding
import com.example.adaverse.model.countModel
import com.example.adaverse.model.countModelResponse
import com.example.adaverse.model.ottModel
import com.example.adaverse.model.ottResponseModel
import retrofit2.Call
import retrofit2.Response

class GenreActivity : AppCompatActivity(),MyInterface,ClickInterface{
    private lateinit var binding: ActivityGenreBinding
    private lateinit var genreadapter: GenreAdapter
    private lateinit var contentList: ottResponseModel
    var type : String?= null
    var showType: String?=null
    var genre: String?=null
    var username: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)

         type= intent.getStringExtra("type")
        showType= intent.getStringExtra("showType")
        genre=intent.getStringExtra("genre")
        username=intent.getStringExtra("username")
        binding.sortBtn.setOnClickListener {
            var dialog=SortingFragment()
            dialog.show(supportFragmentManager,"customDialog")
        }



        val retrofit= ServiceBuilder.buildService(APIInterface::class.java)
        val obj=ottModel(type,showType, genre!!)

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
                        genreadapter=GenreAdapter(contentList.ott_recommendations,this@GenreActivity,this@GenreActivity,
                            username.toString()
                        )
                        adapter=genreadapter
                        layoutManager=LinearLayoutManager(this@GenreActivity)

                    }
                }

                override fun onFailure(call: Call<ottResponseModel>, t: Throwable) {
                    Log.d("TAG","failed"+t)
                }

            }

        )


    }

    override fun transferredMessage(msg: String) {
        val intent = Intent(this, SortedActivity::class.java).also {
            it.putExtra("Type", type)
            it.putExtra("showType", showType)
            it.putExtra("genre", genre)
            it.putExtra("msg", msg)
            it.putExtra("username",username)
            startActivity(it)
        }
    }

   override fun onCellClickListener(countModel: countModel) {
        val retrofit= ServiceBuilder.buildService(APIInterface::class.java)
        retrofit.count(countModel).enqueue(
            object:retrofit2.Callback<countModelResponse>{
                override fun onResponse(
                    call: Call<countModelResponse>,
                    response: Response<countModelResponse>
                ) {
                    Log.d("TAG",response.body().toString())
                }

                override fun onFailure(call: Call<countModelResponse>, t: Throwable) {
                    Log.d("TAG","error")
                }


            }

        )
    }


}