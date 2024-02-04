package com.example.adaverse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.adaverse.databinding.ActivitySignUpBinding
import com.example.adaverse.model.AuthenticationModel
import com.example.adaverse.model.SignUpResponse
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name= binding.nameEt.text
        val password=binding.passwordEt.text

        binding.registerBtn.setOnClickListener {
            val retrofit= ServiceBuilder.buildService(APIInterface::class.java)
            val obj= AuthenticationModel(name?.toString(), password?.toString())

            retrofit.signup(obj).enqueue(
                object:retrofit2.Callback<SignUpResponse>{
                    override fun onResponse(
                        call: Call<SignUpResponse>,
                        response: Response<SignUpResponse>
                    ) {
                        Log.d("TAG",response.body().toString())
                        function(name.toString())
                    }

                    override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {

                    }


                }

            )
        }

    }

    private fun function(username: String) {
        val intent = Intent(this, HomeActivity::class.java).also {
            it.putExtra("Type", "ott")
            it.putExtra("showType", "movie")
            it.putExtra("genre","Drama")
            it.putExtra("username",username)
            startActivity(it)
        }
    }
}