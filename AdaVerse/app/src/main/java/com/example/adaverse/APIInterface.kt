package com.example.adaverse

import com.example.adaverse.model.AuthenticationModel
import com.example.adaverse.model.LiveTvModelResponse
import com.example.adaverse.model.LoginModel
import com.example.adaverse.model.SignUpResponse
import com.example.adaverse.model.SortModel
import com.example.adaverse.model.countModel
import com.example.adaverse.model.countModelResponse
import com.example.adaverse.model.ottModel
import com.example.adaverse.model.ottResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIInterface {
    @POST("/genrePref")
    fun requestShows(@Body ottModel: ottModel): Call<ottResponseModel>

    @POST("/genreSort")
    fun showSort(@Body sortModel: SortModel): Call<ottResponseModel>

    @POST("/login")
    fun login(@Body authenticationModel: AuthenticationModel): Call<LoginModel>


    @POST("/signup")
    fun signup(@Body authenticationModel: AuthenticationModel): Call<SignUpResponse>

    @POST("/history")
    fun count(@Body countModel: countModel): Call<countModelResponse>

    @POST("/livetv")
    fun liveTv(@Body loginModel: LoginModel): Call<LiveTvModelResponse>
}