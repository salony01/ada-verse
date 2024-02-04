package com.example.adaverse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.adaverse.databinding.ActivityLoginBinding
import com.example.adaverse.model.AuthenticationModel
import com.example.adaverse.model.LoginModel
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name= binding.nameEt.text
        val password=binding.passwordEt.text
        binding.loginBtn.setOnClickListener{
            val retrofit= ServiceBuilder.buildService(APIInterface::class.java)
            val obj= AuthenticationModel(name?.toString(), password?.toString())

            retrofit.login(obj).enqueue(
                object:retrofit2.Callback<LoginModel>{
                    override fun onResponse(
                        call: Call<LoginModel>,
                        response: Response<LoginModel>
                    ) {
                        Log.d("TAG",response.body().toString())
                        response.body()?.let { it1 -> function(it1.genre_string, name.toString()) }
                    }

                    override fun onFailure(call: Call<LoginModel>, t: Throwable) {

                    }


                }

            )
        }
    }
    private fun function( genre:String,username:String ) {
        val intent = Intent(this, HomeActivity::class.java).also {
            it.putExtra("Type", "ott")
            it.putExtra("showType", "movie")
            it.putExtra("genre",genre)
            it.putExtra("username",username)
            startActivity(it)
        }
    }
}