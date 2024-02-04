package com.example.adaverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adaverse.adapter.GenreAdapter
import com.example.adaverse.databinding.ActivitySortedBinding
import com.example.adaverse.model.SortModel
import com.example.adaverse.model.ottResponseModel
import retrofit2.Call
import retrofit2.Response

class SortedActivity : AppCompatActivity() {
    public lateinit var binding: ActivitySortedBinding
    var ratval : Boolean?= false
    var yearval: Boolean?=false
    var username: String?=null
    private lateinit var contentList: ottResponseModel
    private lateinit var genreadapter: GenreAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySortedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type= intent.getStringExtra("type")
        val showType= intent.getStringExtra("showType")
        val genre=intent.getStringExtra("genre")
        val message=intent.getStringExtra("msg")
        val username=intent.getStringExtra("username")

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
        if(message=="Sort by rating"){
            ratval=true
        }
        else{
            yearval=true
        }
        val retrofit= ServiceBuilder.buildService(APIInterface::class.java)
        val obj= SortModel(type,showType, genre!!, ratval!!, yearval!!)
        retrofit.showSort(obj).enqueue(
            object:retrofit2.Callback<ottResponseModel>{
                override fun onResponse(
                    call: Call<ottResponseModel>,
                    response: Response<ottResponseModel>
                ) {

                    Log.d("TAG",response.body().toString())
                    contentList= response.body()!!
                    binding.recyclerView.apply {
                        genreadapter= GenreAdapter(contentList.ott_recommendations,this@SortedActivity,null,
                            username.toString()
                        )
                        adapter=genreadapter
                        layoutManager= LinearLayoutManager(this@SortedActivity)

                    }
                }

                override fun onFailure(call: Call<ottResponseModel>, t: Throwable) {
                    Log.d("TAG","failed"+t)
                }

            }

        )
    }
}