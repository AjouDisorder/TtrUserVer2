package com.example.ttruserver2.Retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

data class ResponseDTO(var result:String? = null, var userId: String?=null)
data class ResponseBInfo(
    var result: String? = null,
    var _id: String? = null,
    var userId: String? = null
)

//data class Data(val data: Accepted)
//data class Accepted(val accepted: HashMap<String, Friend>)
//data class Friend(val success: Boolean, val message: String)
//data class ResponseDTO()

interface IMyService{
    /*
    @FormUrlEncoded
    @POST("boss/signup")
    fun joinBoss(@Field("bossId") bossId: String,
                 @Field("password") password: String,
                 @Field("name") name: String
                 //@Field("age") age: String,
                 //@Field("sex") sex: String,
                 //@Field("phone") phone: String
    ): Call<ResponseDTO>

    @FormUrlEncoded
    @POST("boss/login")
    fun loginBoss(@Field("bossId") email: String,
                  @Field("password") password: String): Call<ResponseDTO>
*/
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
                  @Field("password") password: String): Call<ResponseBInfo>

//    @GET("/boss/getRestaurantList")
//    fun getRestaurant(@Query("boss_id") id:String): Call<ResponseBody>
//    /user/getMenuListOfRestaurant
    @GET("/user/getMenuListOfRestaurant")
    fun getMenuListOfRestaurant(@Query("restaurant_id") restaurant_id:String): Call<ResponseBody>

    @GET("/user/getMenuByTime")
    fun getMenuByTime(@Query("year") year:Int,
                      @Query("month") month:Int,
                      @Query("date") date:Int,
                      @Query("hour") hour:Int,
                      @Query("min") min:Int,
                      @Query("lat") lat:Double,
                      @Query("lng") lng:Double): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/user/createTicket")
    fun createTicket(@Field("menu_id") menu_id: String,
                     @Field("quantity") quantity: Int,
                     @Field("user_id") user_id: String,
                     @Field("totalPrice") totalPrice: Int,
                     @Field("method") method: String,
                     @Field("messageForBoss") messageForBoss: String,
                     @Field("restaurantTitle") restaurantTitle: String): Call<ResponseBody>

    @GET("/user/getTicketList")
    fun getTicketList(@Query("user_id") user_id : String): Call<ResponseBody>


//    /user/createTicket
//    /user/getTicketList

}