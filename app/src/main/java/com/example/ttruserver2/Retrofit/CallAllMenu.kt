package com.example.ttruserver2.Retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

data class AllMenu(var allMenu_allMenu: AllMenu? = null, var allMenu_result:String? = null)

interface CallAllMenu {
    @FormUrlEncoded
    @POST("user/signup")
    fun signUpUser(@Field("userId") userId: String,
                   @Field("password") password: String,
                   @Field("nickname") nickname: String
        //@Field("age") age: String,
        //@Field("sex") sex: String,
        //@Field("phone") phone: String
    ): Call<ResponseDTO>

    @FormUrlEncoded
    @POST("user/login")
    fun loginUser(@Field("userId") email: String,
                  @Field("password") password: String): Call<ResponseDTO>
}